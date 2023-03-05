package com.example.imserver.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String[]> params;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public ParameterRequestWrapper(HttpServletRequest request, Map<String, String[]> newParams) {
        super(request);
        this.params = newParams;
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
    public Enumeration getParameterNames() {
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
