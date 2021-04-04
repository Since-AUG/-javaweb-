package com.bookshop.filter;

import com.bookshop.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.rmi.RemoteException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e){
            e.printStackTrace();
            JDBCUtils.commitAndClose();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
