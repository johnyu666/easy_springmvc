package cn.johnyu.easyspring.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.johnyu.easyspring.ApplicationContext;
import cn.johnyu.easyspring.HandlerAdapter;
import cn.johnyu.easyspring.HandlerExecutionChain;
import cn.johnyu.easyspring.HandlerMapping;
import cn.johnyu.easyspring.HandlerMethod;
import cn.johnyu.easyspring.ModelAndView;
import cn.johnyu.easyspring.RequestInfo;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;
import cn.johnyu.easyspring.interceptor.HandlerInterceptor;

public class DispatcherServlet extends HttpServlet {
	private HandlerMapping handlerMapping;
	private HandlerAdapter adapter;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext.getInstance();
		handlerMapping = new HandlerMapping();
		adapter = new HandlerAdapter();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestInfo info = parseRequestInfo(req);

		HandlerMethod handlerMethod = handlerMapping.getMapping().get(info);
		HandlerExecutionChain chain = handlerMapping.getExecutoinChain();
		try {
			// 前置拦截方法
			for (HandlerInterceptor interc : chain.getInterceptors())
				interc.preHandle(req, resp, handlerMethod.getHandler());
			adapter.setHandlerMethod(handlerMethod);
			ModelAndView mv = adapter.process();
			// 后置拦截方法
			for (HandlerInterceptor interc : chain.getInterceptors())
				interc.postHandle(req, resp, handlerMethod.getHandler(), mv);
			resp.getWriter().write(req.getMethod() + "\t" + req.getServletPath().substring(1));
			// 后置拦截方法
			for (HandlerInterceptor interc : chain.getInterceptors())
				interc.afterCompletion(req, resp, handlerMethod.getHandler(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private RequestInfo parseRequestInfo(HttpServletRequest req) {
		String requestMethod = req.getMethod();
		RequestInfo info = new RequestInfo();
		info.setPath(req.getServletPath().substring(1));

		switch (requestMethod) {
		case "GET":
			info.setMethods(new RequestMethodType[] { RequestMethodType.GET });
			break;
		case "POST":
			info.setMethods(new RequestMethodType[] { RequestMethodType.POST });
			break;
		default:
			break;
		}
		return info;
	}
}
