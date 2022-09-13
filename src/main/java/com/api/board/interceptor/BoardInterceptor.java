package com.api.board.interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
public class BoardInterceptor implements HandlerInterceptor {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Override
    public boolean preHandle( HttpServletRequest request
    		                , HttpServletResponse response
    		                , Object handler ) throws Exception {
        
    	 if (logger.isDebugEnabled()) {
             logger.debug("===== ===== ===== ===== =====    START     ===== ===== ===== ===== =====");
             logger.debug("RequestURI:[{}]", request.getRequestURI());
         }
    	
        return true;
    }
 
    @Override
    public void postHandle( HttpServletRequest request
    		              , HttpServletResponse response
    		              , Object handler
    		              , ModelAndView modelAndView ) throws Exception {
        
    	 if (logger.isDebugEnabled()) {
             logger.debug("===== ===== ===== ===== =====     END     ===== ===== ===== ===== =====\n");
         }
    }
 
    @Override
    public void afterCompletion( HttpServletRequest request
    		                   , HttpServletResponse response
    		                   , Object handler
    		                   , Exception ex ) throws Exception {
    }
}