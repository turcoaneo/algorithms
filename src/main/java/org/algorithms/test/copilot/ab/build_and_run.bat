@echo off
javac --release 17 -d out ABStringSolution.java
jar cfm ABStringSolution.jar manifest.txt -C out . ABStringSolution.java README.txt
java -jar ABStringSolution.jar -Xmx128M
pause