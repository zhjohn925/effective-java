package effective_java;

// javac -d class_path FileReadDemo01.java
// java -cp class_path effective_java.FileReadDemo01

import java.io.*;

public class FileReadDemo01 {
	public static void main(String[] args) throws Exception {
		File f = new File("abc.txt");
		FileReader fr1 = new FileReader(f);
		char[] ch = new char[(int)f.length()];
		int cnt = fr1.read(ch);
		for (char c : ch) {
			System.out.print(c);
		}
		System.out.println("file size "+cnt);
		System.out.println("======================");
		FileReader fr2 = new FileReader("abc.txt");
		int i = fr2.read();
		while (i!=-1) {
			System.out.print((char)i);
			i = fr2.read();
		} 
		System.out.println();
	}
}