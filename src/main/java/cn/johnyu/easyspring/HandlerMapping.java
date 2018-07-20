package cn.johnyu.easyspring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;
import cn.johnyu.easyspring.interceptor.HandlerInterceptor;
import cn.johnyu.util.JohnReflectUtil;
import controller.UserController;

public class HandlerMapping {
	private ApplicationContext context = ApplicationContext.getInstance();
	private Map<String, Object> iocContainer = new HashMap<String, Object>();
	private Map<RequestInfo, HandlerMethod> mapping = new HashMap<RequestInfo, HandlerMethod>();
	private HandlerExecutionChain chain;
	public HandlerMapping() {
		chain=new HandlerExecutionChain();
		scanPackage();
	}
	public Map<RequestInfo, HandlerMethod> getMapping() {
		return mapping;
	}
	public void scanPackage() {
		List<Class> list = JohnReflectUtil.createClassList("");
		createMapping(list);
	}

	public void createMapping(List<Class> clzes) {
		for (Class clz : clzes) {
			Annotation an = clz.getDeclaredAnnotation(Controller.class);
			if (an != null) {
				Method[] methods = clz.getDeclaredMethods();
				for(Method method:methods) {
					RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
					if (requestMapping != null) {
						RequestInfo info = new RequestInfo();
						info.setPath(requestMapping.value());
						info.setMethods(requestMapping.method());
						
						HandlerMethod handlerMethod = new HandlerMethod();
						handlerMethod.setMethod(method);
						handlerMethod.setHandler(ApplicationContext.getInstance().getIocContainer().get(clz));
						mapping.put(info, handlerMethod);
					}
				}
			}
			
			
		}
	}
	
	public void addInterceptor(HandlerInterceptor interceptor) {
		this.chain.addInterceptor(interceptor);
	}
	
	public HandlerExecutionChain getExecutoinChain() {
		return this.chain;
	}
	public static void main(String[] args) {
		HandlerMapping mapping = new HandlerMapping();
		Map<RequestInfo, HandlerMethod> map = mapping.getMapping();
		RequestInfo info1=new RequestInfo();	
		RequestMethodType[] type1=new RequestMethodType[1];	
		type1[0]=RequestMethodType.GET;	
		info1.setPath("users");
		info1.setMethods(type1);	
		HandlerMethod m=map.get(info1);
		System.out.println(m);
	}

}
