package cn.johnyu.easyspring;

public interface ApplicationContext {
	public <T> T getBean(Class<T> clazz);
}
