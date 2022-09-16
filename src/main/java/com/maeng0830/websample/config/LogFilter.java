package com.maeng0830.websample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 외부 -> filter (-> 처리 ->) filter -> 외부
        // 괄호 안의 처리 과정을 위해 chain이 필요함
        log.info("Hello filter : " + Thread.currentThread());
        chain.doFilter(request, response);
        log.info("Bye filter : " + Thread.currentThread());
    }
}
