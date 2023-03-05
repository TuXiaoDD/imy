package com.example.imserver.filter;

import com.example.imserver.handler.ParameterRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RequestFilter;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AddParamRequestFilter extends RequestFilter {
    /**
     * 添加 uid
     * @param request The servlet request to be processed
     * @param response The servlet response to be created
     * @param chain The filter chain
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            try {
                Map<String, String[]> parameterMap = request.getParameterMap();
                Map<String, String[]> newParams = new HashMap<>(parameterMap);
                newParams.put("uid", new String[1]);
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                ParameterRequestWrapper wrapper = new ParameterRequestWrapper(httpServletRequest, newParams);
                chain.doFilter(wrapper, response);
                return;
            } catch (Exception e) {
                log.error("AddParamRequestFilter error", e.fillInStackTrace());
                chain.doFilter(request, response);
                return;
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    protected Log getLogger() {
        return null;
    }
}
