package cn.johnyu.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarFile;

public class JohnReflectUtil {

	/**
	 * @param packageName
	 *            包名
	 * @return 递归生成的class的list
	 */
	public static List<Class> createClassList(String packageName) {
		List<Class> list = new ArrayList<Class>();

		String packagePath = packageName.replace(".", File.separator);
		// 如果传入为空字串，则表示为扫描整个项目的包
		if (packagePath.length() == 0) {
			packagePath = ".";
		}
		URL url = Thread.currentThread().getContextClassLoader().getResource(packagePath);
		File[] files = new File(url.getPath()).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				if((packageName.length()) > 0) {
					list.addAll(createClassList(packageName + "." + f.getName()));
				}
				else {
					list.addAll(createClassList(f.getName()));
				}
			} else {
				if (f.getName().endsWith(".class")) {
					int endindex = f.getName().lastIndexOf(".class");
					String className = packageName + "." + f.getName().substring(0, endindex);
					try {
						list.add(Class.forName(className));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}

	public static void main(String[] args) throws Exception{
		// URL url = Thread.currentThread().getContextClassLoader().getResource("./");
		// System.out.println(url.getPath());
		// File[] files = new File(url.getPath()).listFiles();
		// for(File f:files) {
		// System.out.println(f.getName());
		// }
		List<Class> list = createClassList("cn");
		for (Class c : list) {
			System.out.println(c);
		}
//		String s1="cn.johnyu.easyspring.annotation"
//		Class.forName("cn.johnyu.easyspring.annotation.RequestInfo");
	}

}
