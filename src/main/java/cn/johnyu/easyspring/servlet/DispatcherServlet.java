package cn.johnyu.easyspring.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
import cn.johnyu.easyspring.Model;
import cn.johnyu.easyspring.ModelAndView;
import cn.johnyu.easyspring.RequestInfo;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;
import cn.johnyu.easyspring.interceptor.HandlerInterceptor;
import cn.johnyu.easyspring.viewresolver.InternalResourceViewResolver;
import cn.johnyu.easyspring.viewresolver.View;
import cn.johnyu.easyspring.viewresolver.ViewResolver;

public class DispatcherServlet extends HttpServlet {
	private HandlerMapping handlerMapping;
	private HandlerAdapter adapter;
	private InternalResourceViewResolver resolver;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ApplicationContext.getInstance();
		handlerMapping = new HandlerMapping();
		adapter = new HandlerAdapter();
		resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestInfo requestInfo = parseRequestInfo(req);

		HandlerMethod handlerMethod = handlerMapping.getMapping().get(requestInfo);
		//根据path+request method无法定位处理方法，则返回415
		if(handlerMethod==null) {
			resp.setStatus(415);
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().write("请求资源找不到！");
			return;
		}
		
		HandlerExecutionChain chain = handlerMapping.getExecutoinChain();
		req.setAttribute("handlerMethod", handlerMethod);
		
		try {
			// 前置拦截方法
			for (HandlerInterceptor interc : chain.getInterceptors())
				interc.preHandle(req, resp, handlerMethod.getHandler());
			
			//调用Handler的处理方法（核心处理）
			adapter.setHandlerMethod(handlerMethod);
			ModelAndView mv = adapter.process();
			String viewName=mv.getViewName();
			Model model=mv.getModel();
			View view=resolver.resolveViewName(viewName);
			view.render(model, req, resp);
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
		case "PUT":
			info.setMethods(new RequestMethodType[] { RequestMethodType.PUT });
			break;
		case "DELETE":
			info.setMethods(new RequestMethodType[] { RequestMethodType.DELETE });
			break;
		default:
			break;
		}
		return info;
	}
}
