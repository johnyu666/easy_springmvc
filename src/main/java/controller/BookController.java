package controller;

import java.lang.reflect.Method;

import cn.johnyu.easyspring.annotation.Controller;
import cn.johnyu.easyspring.annotation.RequestMapping;
import cn.johnyu.easyspring.annotation.RequestMethodType;

@Controller
public class BookController {
	public BookController() {
		System.out.println("book controller structing....");
	}
	@RequestMapping(value="books",method= {RequestMethodType.GET})
	public String addBook() {
		return "addBookSuc";
	}
}
