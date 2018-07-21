package cn.johnyu.easyspring.viewresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.johnyu.easyspring.Model;

public interface View {
	public void render(Model model,HttpServletRequest request,HttpServletResponse resp);
}
