package controller;

import cn.johnyu.easyspring.Model;
import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;

@Controller
public class UserController {
	public UserController() {
		
	}
	@RequestMapping(value="users",method={RequestMethodType.POST})
	public String addUser(String uname,int age,boolean sex,Model model) {
		System.out.println(model+"\t"+uname+"\t"+age+"\t"+sex);
		System.out.println(this+"\t add user....");
		return "add a User";
	}
	@RequestMapping(value="users",method={RequestMethodType.GET})
	public String findAllUser() {
		System.out.println(this+"\t find allusers....");
		return "findAllUser";
	}
}
