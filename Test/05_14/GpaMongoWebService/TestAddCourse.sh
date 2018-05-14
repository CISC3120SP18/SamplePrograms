#!/bin/sh 

curl -s --insecure \
	--header "Content-Type: application/json" \
  	--request POST \
  	--data '{"studentId":"01", "number":"cisc3120", "title":"Design and Implementation of Software Applications I",  "term":"Spring 2018", "creditHours":3, "grade":"A"}' \
  https://localhost:8443/gpa/addcourse?studentid=01
