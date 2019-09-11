package com.thtf.ezone.ezmap.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *  字符集过滤器
 */
public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	public SetCharacterEncodingFilter() {
		super();

	}

	/*
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");

	}

	/*
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		// Select and set (if needed) the character encoding to be used
		String encoding = selectEncoding(request);

		if (encoding != null)
			request.setCharacterEncoding(encoding);

		// Pass control on to the next filter
		chain.doFilter(request, response);

	}

	/*
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;

	}

	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

}
