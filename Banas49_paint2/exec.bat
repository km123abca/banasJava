set inpfil=Lesson49
set isdb=no
cls


set /p path=<pat.txt

javac %inpfil%.java
if %isdb% equ yes (
java -classpath ".;D:\Programming\mysql-connector-java-8.0.12.jar"  %inpfil%
)
if %isdb% equ no ( 
java %inpfil%
)
pause
