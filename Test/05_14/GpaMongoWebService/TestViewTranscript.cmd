@ECHO OFF
@SET PATH=C:\Applications\curl-7.59.0\src;%PATH%
curl -s --insecure ^
	--header "Content-Type: application/json" ^
  	--request GET ^
  https://localhost:8443/gpa/viewtranscript/01
