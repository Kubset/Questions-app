package com.question.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Component
public class LoggerInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String reponseMessage = "RESPONSE[{}]{} {}";
        int status = response.getStatus()/100;

        switch(status) {
            case 1:
            case 2:
                log.info(reponseMessage, response.getStatus(), request.getMethod(), request.getRequestURL());
                break;
            case 3:
            case 4:
            default:
                log.warn(reponseMessage, response.getStatus(), request.getMethod(), request.getRequestURL());
                break;
            case 5:
                log.error(reponseMessage, response.getStatus(), request.getMethod(), request.getRequestURL());
                break;
        }
    }
}
