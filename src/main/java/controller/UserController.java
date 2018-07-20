package controller;

import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;

@Controller
public class UserController {
	public UserController() {
		
	}
	@RequestMapping(value="users",method={RequestMethodType.POST})
	public String addUser() {
		System.out.println(this+"\t add user....");
		return "add a User";
	}
	@RequestMapping(value="users",method={RequestMethodType.GET})
	public String findAllUser() {
		System.out.println(this+"\t find allusers....");
		return "findAllUser";
	}
}
