package pojo;

public class User {
	private Integer id;
	private String uname;
	private boolean sex;
	private int age;
	
	public User(Integer id, String uname, boolean sex, int age) {
		super();
		this.id = id;
		this.uname = uname;
		this.sex = sex;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static void main(String[] args) {
		
	}
}
