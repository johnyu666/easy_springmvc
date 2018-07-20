package cn.johnyu.easyspring;

public class ModelAndView {
	private Model model;
	private String view;
	public ModelAndView(Model model, String view) {
		super();
		this.model = model;
		this.view = view;
	}
	public Model getModel() {
		return model;
	}
	public String getView() {
		return view;
	}
	
}
