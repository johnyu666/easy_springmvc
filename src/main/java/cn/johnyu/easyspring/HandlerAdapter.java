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
			view = (String)method.invoke(handler);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Model model=new Model();
		ModelAndView mv=new ModelAndView(model, view);
		return mv;
		
		
	}
}
