package effective_java;

// javac -d class_path Item9y_compare.java 
// java -cp class_path effective_java.Item9y_compare

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

//Refer to readme.equals
//-All wrapper classes and String class implement 
// Comparable interface as shown in test1(), test2().
//-String is final class which not allow inherent. Hence 
// not able to write subclass to override Comparable for
// String object.
//-To create your own sort algorithm, test3() Shows
// how by implementing anonymous Comparator interface 
//

class User implements Comparable<User> {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	//override the method in Comparable interface
	@Override
	public int compareTo(User user) {
		return this.username.compareTo(user.getUsername());
	}
}

public class Item9y_compare {

	public void test1() {
		User user1 = new User();
		user1.setUsername("tpage");
		user1.setPassword("password!");
		User user2 = new User();
		user2.setUsername("zpage");
		user2.setPassword("password!");

		// Comparable, Comparator
		// 2 interfaces defined by java -> sort

		List<User> list = new ArrayList<User>();
		list.add(user2);
		list.add(user1);
		Collections.sort(list);

		System.out.println(list.get(0).getUsername());  //tpage
		System.out.println(list.get(1).getUsername());  //zpage 
	}

	public void test2() {
		String str1 = "tpage";
		String str2 = "Zpage";

		List<String> list = new ArrayList<String>();
		list.add(str2);
		list.add(str1);

		Collections.sort(list);

		System.out.println(list.get(0));  //Zpage
		System.out.println(list.get(1));	//tpage	
	}

	public void test3() {
		String str1 = "tpage";
		String str2 = "Zpage";

		List<String> list = new ArrayList<String>();
		list.add(str2);
		list.add(str1);

		Collections.sort(list, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			}
		});

		System.out.println(list.get(0));  //tpage, which is different
		System.out.println(list.get(1));	//Zpage, from test2	
	}

	public static void main(String[] args) {
		Item9y_compare item = new Item9y_compare();
		item.test1();
		item.test2();
		item.test3();
	}

}