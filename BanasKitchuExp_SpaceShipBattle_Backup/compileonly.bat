set inpfil=GameBoard
set runtoo=no

set /p path=<pat.txt
javac %inpfil%.java
if %runtoo% equ yes (
java %inpfil%
)

pause
