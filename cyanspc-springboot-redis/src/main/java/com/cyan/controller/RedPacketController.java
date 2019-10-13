package com.cyan.controller;

import com.cyan.mapper.RedPacketInfoMapper;
import com.cyan.mapper.RedPacketRecordMapper;
import com.cyan.pojo.RedPacketInfo;
import com.cyan.pojo.RedPacketInfoExample;
import com.cyan.pojo.RedPacketRecord;
import com.cyan.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by cyan
 * Date:2019/9/21 19:37
 */
@RestController
@RequestMapping(value = "redPacketController")
public class RedPacketController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedPacketInfoMapper redPacketInfoMapper;

    @Autowired
    private RedPacketRecordMapper redPacketRecordMapper;

    private static final String TOTAL_NUM = "_totalNum";
    private static final String TOTAL_AMOUNT = "_totalAmount";

    /***
     * 发红包
     * @param uid
     * @param totalNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPacket")
    public String saveRedPacket(Integer uid, Integer totalNum, Integer totalAmount) {
        RedPacketInfo record = new RedPacketInfo();
        record.setUid(uid);
        record.setTotalAmount(totalAmount);
        record.setTotalPacket(totalNum);
        record.setCreateTime(new Date());
        record.setRemainingAmount(totalAmount);
        record.setRemainingPacket(totalNum);
        long redPacketId = System.currentTimeMillis();//此时无法保证红包id唯一，最好是用雪花算法进行生成分布式系统唯一键
        record.setRedPacketId(redPacketId);
        redPacketInfoMapper.insert(record);
        redisService.set(redPacketId + "_totalNum", totalNum + "");
        redisService.set(redPacketId + "_totalAmount", totalAmount + "");
        return "success";
    }


    /**
     * 抢红包
     * @param redPacketId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPacket")
    public Integer getRedPacket(long redPacketId) {
        String redPacketName = redPacketId + TOTAL_NUM;
        String num = (String) redisService.get(redPacketName);
        if (StringUtils.isNotBlank(num)) {
            return Integer.parseInt(num);
        }
        return 0;
    }


    /**
     * 拆红包
     * @param redPacketId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRedPacketMoney")
    public String getRedPacketMoney(int uid, long redPacketId) {
        Integer randomAmount = 0;
        String redPacketName = redPacketId + TOTAL_NUM;
        String totalAmountName = redPacketId + TOTAL_AMOUNT;
        String num = (String) redisService.get(redPacketName);
        if (StringUtils.isBlank(num) || Integer.parseInt(num) == 0) {
            return "抱歉！红包已经抢完了";
        }
        String totalAmount = (String) redisService.get(totalAmountName);
        if (StringUtils.isNotBlank(totalAmount)) {
            Integer totalAmountInt = Integer.parseInt(totalAmount);
            Integer totalNumInt = Integer.parseInt(num);
            if(totalNumInt.intValue() == 1){
                randomAmount = totalAmountInt;
            }else{
                Integer maxMoney = totalAmountInt / totalNumInt * 2;
                Random random = new Random();
                randomAmount = random.nextInt(maxMoney);
                if(randomAmount.intValue()==0){
                    randomAmount = 1;
                }
            }
        }
        //课堂作业：lua脚本将这两个命令一起请求
        redisService.decr(redPacketName, 1);
        redisService.decr(totalAmountName,randomAmount);  //redis decreby功能
        updateRacketInDB(uid, redPacketId,randomAmount);
        return randomAmount + "";
    }

    public void updateRacketInDB(int uid, long redPacketId, int amount) {
        RedPacketRecord redPacketRecord = new RedPacketRecord();
        redPacketRecord.setUid(uid);
        redPacketRecord.setRedPacketId(redPacketId);
        redPacketRecord.setAmount(amount);
        redPacketRecord.setCreateTime(new Date());
        redPacketRecord.setUpdateTime(new Date());
        redPacketRecordMapper.insertSelective(redPacketRecord);
        //这里应该查出RedPacketInfo的数量，将总数量和总金额减去
        RedPacketInfoExample redPacketInfoExample = new RedPacketInfoExample();
        redPacketInfoExample.or().andRedPacketIdEqualTo(redPacketId);
        List<RedPacketInfo> redPacketInfoList = redPacketInfoMapper.selectByExample(redPacketInfoExample);
        if(redPacketInfoList != null && redPacketInfoList.size() > 0){
            RedPacketInfo redPacketInfo = new RedPacketInfo();
            redPacketInfo.setId(redPacketInfoList.get(0).getId());
            redPacketInfo.setRemainingAmount(redPacketInfoList.get(0).getRemainingAmount()-amount);
            redPacketInfo.setRemainingPacket(redPacketInfoList.get(0).getRemainingPacket()-1);
            redPacketInfo.setUpdateTime(new Date());
            Integer row = redPacketInfoMapper.updateByPrimaryKeySelective(redPacketInfo);
            //redisService.decr(redPacketId+TOTAL_NUM,1);
            //redisService.decr(redPacketId+TOTAL_AMOUNT,amount);
        }
    }
}
