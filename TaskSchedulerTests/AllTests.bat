set VAR_1="localhost:3000"
set VAR_2="live"
call cd C:\Users\danil\OneDrive\Desktop\FinalProject\automatedTests\AutomatedTestsFP
call mvn test -Dtest=LoginTest,SignupTest -Dexec.domain="localhost:3000"
py "C:\Users\danil\OneDrive\Desktop\FinalProject\automatedTests\AutomatedTestsFP\sendemail.py" %1 %VAR_1% %VAR_2%