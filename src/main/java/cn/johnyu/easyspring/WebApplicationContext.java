package cn.johnyu.easyspring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.util.JohnReflectUtil;
import controller.UserController;

public class WebApplicationContext implements ApplicationContext{
	private Map<Class, Object> iocContainer=new HashMap<Class,Object>();
	private static WebApplicationContext context;
	private WebApplicationContext() {
		List<Class> list=JohnReflectUtil.createClassList("");
		for(Class clz:list) {
			if(clz.getDeclaredAnnotation(Controller.class)!=null) {
				try {
					iocContainer.put(clz, clz.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static WebApplicationContext getInstance() {
		if(context==null) {
			context=new WebApplicationContext();
		}
		return context;
	}
	public static void main(String[] args) {
		UserController uc=WebApplicationContext.getInstance().getBean(UserController.class);
		System.out.println(uc);
	}

	@Override
	public <T> T getBean(Class<T> clazz) {
		return (T)iocContainer.get(clazz);
	}
	
}
