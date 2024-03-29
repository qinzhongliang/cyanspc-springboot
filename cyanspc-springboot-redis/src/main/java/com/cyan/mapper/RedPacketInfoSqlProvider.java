package com.cyan.mapper;

import com.cyan.pojo.RedPacketInfo;
import com.cyan.pojo.RedPacketInfoExample.Criteria;
import com.cyan.pojo.RedPacketInfoExample.Criterion;
import com.cyan.pojo.RedPacketInfoExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;
@Mapper
public class RedPacketInfoSqlProvider {

    public String countByExample(RedPacketInfoExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("red_packet_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(RedPacketInfoExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("red_packet_info");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(RedPacketInfo record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("red_packet_info");
        
        if (record.getRedPacketId() != null) {
            sql.VALUES("red_packet_id", "#{redPacketId,jdbcType=BIGINT}");
        }
        
        if (record.getTotalAmount() != null) {
            sql.VALUES("total_amount", "#{totalAmount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPacket() != null) {
            sql.VALUES("total_packet", "#{totalPacket,jdbcType=INTEGER}");
        }
        
        if (record.getRemainingAmount() != null) {
            sql.VALUES("remaining_amount", "#{remainingAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRemainingPacket() != null) {
            sql.VALUES("remaining_packet", "#{remainingPacket,jdbcType=INTEGER}");
        }
        
        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(RedPacketInfoExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("red_packet_id");
        sql.SELECT("total_amount");
        sql.SELECT("total_packet");
        sql.SELECT("remaining_amount");
        sql.SELECT("remaining_packet");
        sql.SELECT("uid");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.FROM("red_packet_info");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        RedPacketInfo record = (RedPacketInfo) parameter.get("record");
        RedPacketInfoExample example = (RedPacketInfoExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("red_packet_info");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getRedPacketId() != null) {
            sql.SET("red_packet_id = #{record.redPacketId,jdbcType=BIGINT}");
        }
        
        if (record.getTotalAmount() != null) {
            sql.SET("total_amount = #{record.totalAmount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPacket() != null) {
            sql.SET("total_packet = #{record.totalPacket,jdbcType=INTEGER}");
        }
        
        if (record.getRemainingAmount() != null) {
            sql.SET("remaining_amount = #{record.remainingAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRemainingPacket() != null) {
            sql.SET("remaining_packet = #{record.remainingPacket,jdbcType=INTEGER}");
        }
        
        if (record.getUid() != null) {
            sql.SET("uid = #{record.uid,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("red_packet_info");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("red_packet_id = #{record.redPacketId,jdbcType=BIGINT}");
        sql.SET("total_amount = #{record.totalAmount,jdbcType=INTEGER}");
        sql.SET("total_packet = #{record.totalPacket,jdbcType=INTEGER}");
        sql.SET("remaining_amount = #{record.remainingAmount,jdbcType=INTEGER}");
        sql.SET("remaining_packet = #{record.remainingPacket,jdbcType=INTEGER}");
        sql.SET("uid = #{record.uid,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        RedPacketInfoExample example = (RedPacketInfoExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(RedPacketInfo record) {
        SQL sql = new SQL();
        sql.UPDATE("red_packet_info");
        
        if (record.getRedPacketId() != null) {
            sql.SET("red_packet_id = #{redPacketId,jdbcType=BIGINT}");
        }
        
        if (record.getTotalAmount() != null) {
            sql.SET("total_amount = #{totalAmount,jdbcType=INTEGER}");
        }
        
        if (record.getTotalPacket() != null) {
            sql.SET("total_packet = #{totalPacket,jdbcType=INTEGER}");
        }
        
        if (record.getRemainingAmount() != null) {
            sql.SET("remaining_amount = #{remainingAmount,jdbcType=INTEGER}");
        }
        
        if (record.getRemainingPacket() != null) {
            sql.SET("remaining_packet = #{remainingPacket,jdbcType=INTEGER}");
        }
        
        if (record.getUid() != null) {
            sql.SET("uid = #{uid,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, RedPacketInfoExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}
