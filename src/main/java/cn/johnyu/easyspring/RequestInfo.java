package cn.johnyu.easyspring;

import java.util.Arrays;

import cn.johnyu.easyspring.annotation.RequestMethodType;

public class RequestInfo {
	private String path;
	private RequestMethodType[] methods;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public RequestMethodType[] getMethods() {
		return methods;
	}
	public void setMethods(RequestMethodType[] methods) {
		this.methods = methods;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(methods);
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestInfo other = (RequestInfo) obj;
		if (!Arrays.equals(methods, other.methods))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	public static void main(String[] args) {
		RequestInfo info1=new RequestInfo();
		RequestInfo info2=new RequestInfo();
		RequestMethodType[] type1=new RequestMethodType[1];
		RequestMethodType[] type2=new RequestMethodType[1];
		type1[0]=RequestMethodType.POST;
		type2[0]=RequestMethodType.POST;
		info1.setPath("users");
		info1.setMethods(type1);
		info2.setPath("users");
		info2.setMethods(type2);
		System.out.println(info1.equals(info2));
	}
}
