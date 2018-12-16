set inpfil=GameBoard
set isdb=no
set comp=yes
cls


set /p path=<pat.txt
if %comp% equ yes (
javac RoadBrick.java
)


javac %inpfil%.java
if %isdb% equ yes (
java -classpath ".;D:\Programming\mysql-connector-java-8.0.12.jar"  %inpfil%
)
if %isdb% equ no ( 
java %inpfil%
)
pause
