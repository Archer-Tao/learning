package com.tzy.filter;

import org.owasp.esapi.ESAPI;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * Filters Http requests and removes malicious characters/strings (i.e. XSS) from the Query String
 */

public class XssFilter implements Filter {
  static class XSSRequestWrapper extends HttpServletRequestWrapper {
    private ServletContext servletContext;

    public XSSRequestWrapper(ServletContext servletContext, HttpServletRequest request) {
      super(request);
      this.servletContext = servletContext;
    }

    @Override
    public String getParameter(String name) {
      String   parameter = null;
      String[] vals      = getParameterMap().get(name);

      if (vals != null && vals.length > 0) {
        parameter = vals[0];
      }

      return parameter;
    }

    @Override
    public String[] getParameterValues(String name) {
      return getParameterMap().get(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
      return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public Map<String, String[]> getParameterMap() {
      Map<String, String[]> res                 = new HashMap<String, String[]>();
      Map<String, String[]> originalQueryString = super.getParameterMap();
      if (originalQueryString != null) {
        for (String key : (Set<String>) originalQueryString.keySet()) {
          String[] rawVals = originalQueryString.get(key);
          String[] snzVals = new String[rawVals.length];
          for (int i = 0; i < rawVals.length; i++) {
            snzVals[i] = stripXSS(rawVals[i]);
          }
          res.put(stripXSS(key), snzVals);
        }
      }
      return res;
    }

    /**
     * Removes all the potentially malicious characters from a string
     *
     * @param value the raw string
     * @return the sanitized string
     */
    private String stripXSS(String value) {
      // NOTE: It's highly recommended to use the ESAPI(https://code.google.com/p/owasp-esapi-java/) library and uncomment the following line to
      // avoid encoded attacks.
      // value = ESAPI.encoder().canonicalize(value);
      return ESAPI.encoder().canonicalize(value);
    }
  }

  private ServletContext _servletContext;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    _servletContext = filterConfig.getServletContext();
    _servletContext.log("XssFilter: init()");
  }

  @Override
  public void destroy() {
    _servletContext.log("XssFilter: destroy()");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    XSSRequestWrapper wrapper = new XSSRequestWrapper(_servletContext, (HttpServletRequest) request);
    chain.doFilter(wrapper, response);
  }

}
