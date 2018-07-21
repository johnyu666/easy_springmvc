package controller;

import java.util.ArrayList;
import java.util.List;

import cn.johnyu.easyspring.Model;
import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;
import pojo.User;

@Controller
public class UserController {
	public UserController() {
		
	}
	@RequestMapping(value="users",method={RequestMethodType.POST})
	public String addUser(String uname,int age,boolean sex,Model model) {
		model.put("uname", uname);
		return "addUserSuc";
	}
	@RequestMapping(value="users",method={RequestMethodType.GET})
	public String findAllUser(Model model) {
		List<User> users=new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User u=new User(i, "john"+i, true, i*10);
			users.add(u);
		}
		model.put("users", users);
		return "allUsers";
	}
}
