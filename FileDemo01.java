package effective_java;

// javac -d class_path FileDemo01.java
// java -cp class_path effective_java.FileDemo01

import java.io.File;

public class FileDemo01 {
	//print the files and directories 
	//   in the current directory
	public static void main(String[] args) {
		int tcount = 0;  //total count
		int fcount = 0;  //file count
		int dcount = 0;  //directory count
		File d = new File(".");
		String[] l = d.list();
		for (String s : l) {
			tcount++;
			System.out.println(s); //file and directory
			File f = new File(d, s);			 
			if (f.isFile())       fcount++;
			if (f.isDirectory())  dcount++;
		}
		System.out.println("The total count is "+tcount);
		System.out.println("The file count is "+fcount);
		System.out.println("The directory count is "+dcount);
	}
}