set inpfil=Banas32
cls


set /p path=<pat.txt
javac %inpfil%.java 
java %inpfil%
pause
