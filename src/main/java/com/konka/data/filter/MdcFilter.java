package com.konka.data.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志追踪
 *
 * @author framework-generator
 * @version 1.0.0
 * @date 2018-07-18
 */
@Order(1)
@Component("MdcFilter")
@WebFilter(urlPatterns = "/*", filterName = "MdcFilter")
@Slf4j
public class MdcFilter extends OncePerRequestFilter implements Filter{


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        long startClock = System.currentTimeMillis();
        try {

            dumpRequest(httpServletRequest);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmssSSS");

            String traceId = simpleDateFormat.format(new Date()) + "-" + Thread.currentThread().getId() + "-" + RandomStringUtils.randomNumeric(5);

            MDC.put("traceid", traceId);

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } finally {

            long executeTime = System.currentTimeMillis() - startClock;
            log.debug("request:{}  executeTime:{}",httpServletRequest.getRequestURI(),executeTime);

            MDC.clear();

        }

    }


    private void dumpRequest(HttpServletRequest request){
        try{
            log.debug("********dump request********");
            String uri = request.getRequestURI();
            log.debug("uri->{}",uri);
            String method = request.getMethod();
            log.debug("method->{}",method);
            String contentType = request.getHeader("Content-Type");
            log.debug("Content-Type->{}",contentType);
            String params =  JSON.toJSONString(request.getParameterMap());
            log.debug("params(JSONFormat)->{}",params);
            log.debug("********dump request********");
        }catch (Exception e){
            log.error("********dumpRequest(request) error********",e);
        }

    }

}
