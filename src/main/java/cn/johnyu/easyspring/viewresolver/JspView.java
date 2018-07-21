package cn.johnyu.easyspring.viewresolver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.johnyu.easyspring.Model;

public class JspView implements View {
	private String jspFilePath;

	public JspView(String jspFilePath) {
		this.jspFilePath = jspFilePath;
	}

	@Override
	public void render(Model model, HttpServletRequest request, HttpServletResponse resp) {
		if (model != null) {
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
		}
		try {
			request.getRequestDispatcher(this.jspFilePath).forward(request, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
