package com.fellaverse.backend.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;


/**
 * @author: Liu Yuehui
 * @ClassName: RequestInterceptor
 * @date: 2022/12/16 19:01
 * @description: Feign请求拦截器（设置请求头，传递登录信息）
 **/
@Slf4j
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor{

    public static final String CONTENT_LENGTH = "content-length";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();

        Assert.notNull(attributes , "FeignBasicAuthRequestInterceptor apply() attributes is null");

        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                // block content-length to fix Incomplete output stream executing GET
                if (CONTENT_LENGTH.equalsIgnoreCase(name)) continue;
                String values = request.getHeader(name);
                requestTemplate.header(name, values);
            }
        }
        Enumeration<String> bodyNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (bodyNames != null) {
            while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                body.append(name).append("=").append(values).append("&");
            }
        }
        if(body.length() != 0) {
            body.deleteCharAt(body.length()-1);
            System.out.println("Body toString: " + body.toString());
            requestTemplate.body(body.toString());
            log.info("feign interceptor body:{}" , body.toString());
        }
    }
}