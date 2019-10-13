package com.cyan.handler;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

public class CyanErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = getErrorAttributes(webRequest, includeStackTrace);
        Map<String,Object> ext = (Map<String,Object>)webRequest.getAttribute("ext", 0);
        errorAttributes.put("company","cyan");
        errorAttributes.put("ext",ext);
        return errorAttributes;
    }
}
