package com.example.imserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RequestFilter;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class AddParamRequestFilter extends RequestFilter {
    /**
     * 添加 uid
     *
     * @param request  The servlet request to be processed
     * @param response The servlet response to be created
     * @param chain    The filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            try {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                ParameterRequestWrapper wrapper = new ParameterRequestWrapper(httpServletRequest, "uid");
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


    /**
     * 给所有请求的请求参数中添加uid参数
     */
    public static class ParameterRequestWrapper extends HttpServletRequestWrapper {
        private final Map<String, String[]> params;

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public ParameterRequestWrapper(HttpServletRequest request, String... keys) {
            super(request);
            Map<String, String[]> newParams = new HashMap<>(request.getParameterMap());
            this.params = newParams;
            if (keys.length == 0) return;
            Arrays.stream(keys).filter(key -> !newParams.containsKey(key)).forEach(key -> newParams.put(key, new String[keys.length]));
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return params;
        }


        @Override
        public String[] getParameterValues(String name) {
            Object v = params.get(name);
            if (v == null) {
                return null;
            } else {
                return (String[]) v;
            }
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(params.keySet());
        }

        @Override
        public String getParameter(String name) {
            Object v = params.get(name);
            if (v == null) {
                return null;
            } else {
                String[] strArr = (String[]) v;
                if (strArr.length > 0) {
                    return strArr[0];
                } else {
                    return null;
                }
            }
        }
    }
}
