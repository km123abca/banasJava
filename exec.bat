set inpfil=tone
cls


set /p path=<pat.txt
javac %inpfil%.java 
java %inpfil%
