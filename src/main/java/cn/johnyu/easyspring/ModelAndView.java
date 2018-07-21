package cn.johnyu.easyspring;

public class ModelAndView {
	private Model model;
	private String viewName;
	public ModelAndView(Model model, String viewName) {
		super();
		this.model = model;
		this.viewName = viewName;
	}
	public Model getModel() {
		return model;
	}
	public String getViewName() {
		return viewName;
	}
	
}
