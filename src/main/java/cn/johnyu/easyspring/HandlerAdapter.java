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
		Model model=null;
		String viewName="";
		for(Object o:handlerMethod.getActualParams()) {
			if(o instanceof Model) {
				model=(Model)o;
				break;
			}
		}
		try {
			viewName = (String)method.invoke(handler,handlerMethod.getActualParams().toArray());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		ModelAndView mv=new ModelAndView(model, viewName);
		return mv;
	}
}
