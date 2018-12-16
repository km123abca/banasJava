set inpfil=GameBoard
set inpfil2=SpaceShip
set isdb=no
set comp=no
cls


set /p path=<pat.txt
if %comp% equ yes (
javac Rock.java
javac SpaceShip.java
javac PhotonTorpedo.java
)

rem javac %inpfil2%.java
javac %inpfil%.java
if %isdb% equ yes (
java -classpath ".;D:\Programming\mysql-connector-java-8.0.12.jar"  %inpfil%
)
if %isdb% equ no ( 
java %inpfil%
)
pause
