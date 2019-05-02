PHMT Developer - Applicant Test
The test code is under Maven Project "PHMT" including source code, UnitTest and pom etc. pom.xml is including dependencies required for project. the code is based on version Java SE 1.8 and the UnitTest is base on JUnit 5.

1. Core Java
The code is under package CoreJava with 3 classes

2. File IO
There are two packages based on requirement 1) & 2): FileIO and TextInputGUI

3. Create a Tree Data structure
The code is under package TreeNode with 2 classes, the UnitTest case is included in UnitTest folder

4. Math & Problem Solving
Class DataProcessor is for calculating all combination of a large numbers of Double type numbers. The rules are as following:
* The class has already have a main method in it, so you can just kindly run the class file to get the result of calculation.
* All data those user entered and those for calculation, will be saved under directory of c:\\temp. *.json for user enter data and calculate*.json for temporary calculation. 
* If any errors when read and write the files under directory of c:\\temp, the log will be recorded by path of "C:\\Temp\\logs\\phm.log". 
To ensure the log file work correctly, please kindly ensure:
1). put log4j.properties under the source package.
2). log4j maven dependency has been downloaded.
