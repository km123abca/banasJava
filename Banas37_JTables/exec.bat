set inpfil=Lesson37_kitchu
cls


set /p path=<pat.txt
javac %inpfil%.java 
java -classpath ".;C:\Users\Administrator\Downloads\here\mysql-connector-java-8.0.12\mysql-connector-java-8.0.12.jar"  %inpfil%
pause
