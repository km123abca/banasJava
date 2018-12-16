set inpfil=Sample
cls


set /p path=<pat.txt
javac %inpfil%.java 
java -classpath ".;sqlite-jdbc-3.23.1.jar"  %inpfil%
pause
