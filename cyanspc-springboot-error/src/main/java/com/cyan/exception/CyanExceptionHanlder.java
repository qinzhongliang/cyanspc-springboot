package com.cyan.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CyanExceptionHanlder {

    @ExceptionHandler(value= CyanException.class)
    @ResponseBody
    public Map<String,Object> dealException(CyanException e, HttpServletRequest request){
        Map<String,Object> retInfo = new HashMap<>();
        retInfo.put("code",e.getCode());
        retInfo.put("msg",e.getMsg());
        return retInfo;
    }

//    @ExceptionHandler(value= CyanException.class)
//    public String dealException(CyanException e, HttpServletRequest request){
//        Map<String,Object> retInfo = new HashMap<>();
//        retInfo.put("code",e.getCode());
//        retInfo.put("msg",e.getMsg());
//        return "forward:/error";
//    }

//    @ExceptionHandler(value= CyanException.class)
//    public String dealException(CyanException e, HttpServletRequest request){
//        Map<String,Object> retInfo = new HashMap<>();
//        retInfo.put("code",e.getCode());
//        retInfo.put("msg",e.getMsg());
//        request.setAttribute("javax.servlet.error.status_code",500);
//        request.setAttribute("ext",retInfo);
//        return "forward:/error";
//    }


}
