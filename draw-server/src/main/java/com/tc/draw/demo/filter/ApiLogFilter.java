package com.tc.draw.demo.filter;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class ApiLogFilter implements Filter {
	private final static Log LOG = LogFactory.getLog(ApiLogFilter.class);

	private final static String STATIC_RESOURCE_REG = "/v2/api-docs|/druid|/webjars|/doc.html|/swagger-ui|.js|.png|.css|/swagger-resources";
	
	private final static Pattern P = Pattern.compile(STATIC_RESOURCE_REG);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	private boolean checkStaticResource(String uri) {
		return P.matcher(uri).find();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
		//排除静态页面
		if(checkStaticResource(requestWrapper.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}
		ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
		String reqJson = requestWrapper.getBody();
		Map<String, String[]> map = request.getParameterMap();
		if (map != null && !map.isEmpty() && StringUtils.isEmpty(reqJson)) {
			reqJson = mapToString(map);
		}
		requestWrapper.setAttribute("jsonParam", reqJson);
		// String contentType = requestWrapper.getHeader("content-type");
		chain.doFilter(requestWrapper, responseWrapper);
		long endTime = System.currentTimeMillis();
		// 获取response返回的内容并重新写入response
		byte[] bytes = responseWrapper.getResponseData();
		response.getOutputStream().write(bytes);
		LOG.info(String.format(
				"\n===================Request================>\n地址：%s\nAuthorization:%s\n参数：%s\n方式：%s\nContent-type:%s"
						+ "\n<===================Response================\n状态：%s\n内容：%s\n时长：%s毫秒"
						+ "\n============================================",
				requestWrapper.getRequestURL(), requestWrapper.getHeader("Authorization"), reqJson, requestWrapper.getMethod(),
				requestWrapper.getHeader("content-type"), responseWrapper.getStatus(), new String(bytes, "UTF-8"),
				endTime - startTime));

	}

	private String mapToString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (String key : map.keySet()) {
			sb.append(key).append("=");
			String[] paramValues = map.get(key);
			int len = paramValues.length - 1;
			for (int i = 0; i <= len; i++) {

				sb.append(paramValues[i]);
				if (i != len) {
					sb.append(",");
				}
			}
			sb.append(";");

		}
		return sb.toString();
	}

	@Override
	public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
		LOG.info("过滤器初始化");

	}

}
