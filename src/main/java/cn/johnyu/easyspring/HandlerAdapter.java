package cn.johnyu.easyspring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerAdapter {
	private HandlerMethod handlerMethod;
	public void setHandlerMethod(HandlerMethod handlerMethod) {
		this.handlerMethod = handlerMethod;
	}
	public ModelAndView process() {
		Method method=handlerMethod.getMethod();
		Object handler=handlerMethod.getHandler();
		String view="";
		try {
			view = (String)method.invoke(handler,handlerMethod.getActualParams().toArray());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Model model=new Model();
		ModelAndView mv=new ModelAndView(model, view);
		return mv;
	}
}
