package cn.johnyu.easyspring;

import java.util.ArrayList;
import java.util.List;


import cn.johnyu.easyspring.interceptor.HandlerInterceptor;
import cn.johnyu.easyspring.interceptor.ResourceExposeInterceptor;

public class HandlerExecutionChain {
	private ResourceExposeInterceptor interceptor=new ResourceExposeInterceptor();
	private List<HandlerInterceptor> interceptors=new ArrayList<HandlerInterceptor>();
	private Object hander;
	
	public HandlerExecutionChain() {
		interceptors.add(interceptor);	
	}
	public void addInterceptor(HandlerInterceptor interceptor) {
		interceptors.add(interceptor);
	}
	public List<HandlerInterceptor> getInterceptors() {
		return interceptors;
	}
	public Object getHander() {
		return hander;
	}
	public void setHander(Object hander) {
		this.hander = hander;
	}
	
	
}
