package cn.johnyu.easyspring;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
public class HandlerMethod {
	private Object handler;
	private Method method;
	private List<Object> actualParams=new ArrayList<Object>();
	public Object getHandler() {
		return handler;
	}
	public void setHandler(Object handler) {
		this.handler = handler;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public List<Object> getActualParams() {
		return actualParams;
	}
	
}
