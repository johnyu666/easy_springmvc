package cn.johnyu.easyspring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.util.JohnReflectUtil;
import controller.UserController;

public class ApplicationContext {
	private Map<Class, Object> iocContainer=new HashMap<Class,Object>();
	private static ApplicationContext context;
	private ApplicationContext() {
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
	
	public Map<Class, Object> getIocContainer() {
		return iocContainer;
	}
	public static ApplicationContext getInstance() {
		if(context==null) {
			context=new ApplicationContext();
		}
		return context;
	}
	public static void main(String[] args) {
		Map<Class, Object> map=ApplicationContext.getInstance().getIocContainer();
		System.out.println(map.get(UserController.class));
	}
	
}
