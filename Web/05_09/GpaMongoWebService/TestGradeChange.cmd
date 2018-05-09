@ECHO OFF
@SET PATH=C:\Applications\curl-7.59.0\src;%PATH%
curl -s --insecure ^
	--header "Content-Type: application/json" ^
	--request POST ^
  	--data "{\"studentId\":\"01\", \"number\":\"cisc3120\", \"title\":\"Design and Implementation of Software Applications I\",  \"term\":\"Spring 2018\", \"grade\":\"B\"}" ^
  https://localhost:8443/gpa/gradechange
