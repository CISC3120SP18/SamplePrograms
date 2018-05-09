@ECHO OFF
@SET PATH=C:\Applications\curl-7.59.0\src;%PATH%
curl -s --insecure ^
	--header "Content-Type: application/json" ^
  	--request POST ^
  	--data "{\"sid\":\"01\",\"name\":\"Will\", \"gpa\":3.14}" ^
  https://localhost:8443/gpa/addstudent
