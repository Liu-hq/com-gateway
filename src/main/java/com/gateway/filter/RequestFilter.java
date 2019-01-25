package com.gateway.filter;

import com.gateway.utils.HttpClient;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * Created by Administrator on 2019/1/24.
 */
@Component
public class RequestFilter implements Filter{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HttpClient httpClient;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        String token = req.getHeader("token");//header方式
        boolean isFilter = true;
        String method = ((HttpServletRequest) request).getMethod();
        if (method.equals("OPTIONS")) {
            rep.setStatus(HttpServletResponse.SC_OK);
        }else{
            if (isFilter) {
                String path=req.getRequestURI();
                logger.info("token filter过滤ok! {}",path);
                if(path.indexOf("favicon.ico")>0){
                    chain.doFilter(request,response);
                    return;
                }else{
                    httpClient.client("http:/"+path, method,null);
                    return;
                }
            }
            logger.info("TestFilter1");
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
