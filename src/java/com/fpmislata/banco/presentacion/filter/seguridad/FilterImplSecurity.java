package com.fpmislata.banco.presentacion.filter.seguridad;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class FilterImplSecurity implements Filter{
    FilterConfig filterconfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterconfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        
        String uri = httpServletRequest.getContextPath() + "/api/session/empleado/";
        
        System.out.println(uri);
        HttpSession session = httpServletRequest.getSession(true);
        Integer id = (Integer) session.getAttribute("id");
        Boolean logueado = true;
        
        if(id == null){
            logueado = false;
        }
        
        Boolean permitido = false;
        System.out.println(httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().equals(uri)){
            permitido = true;
        }else{
            if(logueado){
                permitido = true;
            }
        }
        
        if(permitido){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        
    }

    @Override
    public void destroy() {
        
    }
}
