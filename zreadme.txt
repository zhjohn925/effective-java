//Help:
- For questions about using Eclipse
http://help.eclipse.org/neon/index.jsp
- For Java libraries and utilities:
https://docs.oracle.com/javase/7/docs/api/
https://docs.oracle.com/javase/8/docs/api/
- For using Unfolding Maps in the Project:
http://unfoldingmaps.org/tutorials/index.html
http://unfoldingmaps.org/tutorials/mapprovider-and-tiles.html#
http://unfoldingmaps.org/javadoc/, and in particular look at the UnfoldingMap class
https://processing.org/reference/, for background colors/patterns
- A full list of map providers can be found here:
http://unfoldingmaps.org/javadoc/de/fhpotsdam/unfolding/providers/package-summary.html 
- the SQLite library 
https://bitbucket.org/xerial/sqlite-jdbc/
- earthquake data
http://earthquake.usgs.gov   




// Java
- You can only inherit from one class in Java) and as many interfaces as you want.  
- An immutable class is simply a class whose instances cannot be modified. 
  The object is fixed once instantiated. 
  ie. String, the boxed primitive classes, BigInteger and BigDecimal. 

// Start github
$ git config --global user.email "zhjohn925@gmail.com"
$ git config --global user.name "John Zhuang"
$ git clone https://github.com/zhjohn925/effective-java
$ git init
$ git status
$ git add .
$ git commit -m"add item1"
//push to the remote branch "master"
$ git push origin master

//update local from remote repository
$ git pull origin master

//fix merge issue
$ git fetch origin
$ git reset --hard origin/master
$ git pull


// Compile and run java
$ javac -d  class_path  Item002a_builder_pattern.java
$ java  -cp class_path  effective_java/Item002a_builder_pattern 







