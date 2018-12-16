set inpfil=Test2
set isdb=yes
cls


set /p path=<pat.txt

javac %inpfil%.java
if %isdb% equ yes (
rem java -classpath ".;ucanaccess-4.0.4.jar"  %inpfil%
java -classpath ".;ucanaccess-4.0.4.jar;hsqldb.jar;jackcess-2.2.0.jar;commons-logging-1.2.jar;commons-lang3-3.8.1.jar"  %inpfil%
)
if %isdb% equ no ( 
java %inpfil%
)
pause
