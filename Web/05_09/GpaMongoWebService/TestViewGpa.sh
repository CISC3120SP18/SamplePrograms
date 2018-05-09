#!/bin/sh 

curl -s --insecure \
	--header "Content-Type: application/json" \
  	--request GET \
  https://localhost:8443/gpa/viewgpa/01
