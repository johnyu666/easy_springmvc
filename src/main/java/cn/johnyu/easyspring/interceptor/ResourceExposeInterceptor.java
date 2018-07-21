package cn.johnyu.easyspring.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.johnyu.easyspring.HandlerMethod;
import cn.johnyu.easyspring.Model;
import controller.UserController;

public class ResourceExposeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 接收来自DispatcherServlet的调用，从request取出HandlerMethod
		HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute("handlerMethod");
		handlerMethod.getActualParams().clear();//清空实参列表
		
		// 对Handler的方法进行反射，分析其形式参数的类型，进而生成实参列表
		Class clz = handler.getClass();
		Parameter[] params = handlerMethod.getMethod().getParameters();
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Parameter param : params) {
			// 形参类型为Model生成model
			if (param.getType() == Model.class) {
				handlerMethod.getActualParams().add(new Model());
			}
			// 形参数不为Model,处理自动封装
			else {
				String[] paramValues = paramMap.get(param.getName());
				// 形参未出现在请求参数中，做空置处理
				if (paramValues == null) {
					if(param.getType()==int.class)
						handlerMethod.getActualParams().add(0);
					else if(param.getType()==boolean.class)
						handlerMethod.getActualParams().add(false);
					else
						handlerMethod.getActualParams().add(null);
				} else {
					// 否则，并且是单值情况
					if (paramValues.length == 1) {
						// 类型为String的情况
						if (param.getType() == String.class) {
							handlerMethod.getActualParams().add(paramValues[0]);
						}
						// 类型为int的情况
						if (param.getType() == int.class || param.getType() == Integer.class) {
							handlerMethod.getActualParams().add(Integer.parseInt(paramValues[0]));
						}
						// 类型为boolean的情况
						if (param.getType() == boolean.class || param.getType() == Boolean.class) {
							handlerMethod.getActualParams().add(Boolean.parseBoolean(paramValues[0]));
						}

					} else {
						System.out.println("数组情况，暂未做转换处理");
					}
				}
			}

		}

		return true;
	}

	public static void main(String[] args) throws Exception {

		// int m=23;
		// System.out.println();
		// UserController uc = new UserController();
		// Class clz = UserController.class;
		// Method method = clz.getDeclaredMethod("addUser", Model.class);
		// List<Object> trueArgs = new ArrayList<Object>();
		// Parameter[] params = method.getParameters();
		// for (Parameter param : params) {
		// System.out.println("param name is:" + param);
		// if (param.getType() == Model.class) {
		// trueArgs.add(new Model());
		// }
		// }
		// method.invoke(uc, trueArgs.toArray());
	}

}
