# GPA Web Service
This example program demonstrate the following
* how to use Spring RestController to create RESTful Web Service,
* how to use path variables, 
* and how to enable TLS (HTTP over SSL/TLS, or HTTPS. )

## Configure HTTP over SSL/TLS

### Generate SSL/TLS Certificate
HTTP over SSL/TLS is built upon public key crytography. To enable HTTP over SSL/TLS,
we must prepare a SSL/TLS certificate (or simply, a SSL certificate), a public key certificate.
To do this, we first generate a pair of public and private crytographic keys (or simply, 
a public/private key pair). 

The following example shows that we generate a key pair on the command line 
using [`keytool`](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/keytool.html) 
that comes with JDK. 

```
keytool -genkeypair -alias springframework -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
````

Be aware that `keytool` is at the JDK's bin directory. If the directory is not in the search path of your
environment, your environment may report that it cannot find the command. To resolve this, you can either
set the search path to include the JDK's bin directory, or use the absolute path of the `keytool`. In the 
following example transcript, we use the absolute path of `keytool`.  
```
cd C:\Users\CISC3120\SamplePrograms\Web\05_09\GpaWebApp
C:\Users\CISC3120\SamplePrograms\Web\05_09\GpaWebApp>keytool -genkeypair -alias springframework -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
Enter keystore password:
Re-enter new password:
What is your first and last name?
  [Unknown]:  The Instructor
What is the name of your organizational unit?
  [Unknown]:  Computer & Information Science
What is the name of your organization?
  [Unknown]:  CUNY Brooklyn College
What is the name of your City or Locality?
  [Unknown]:  Brooklyn
What is the name of your State or Province?
  [Unknown]:  New York
What is the two-letter country code for this unit?
  [Unknown]:  US
Is CN=The Instructor, OU=Computer & Information Science, O=CUNY Brooklyn College, L=Brooklyn, ST=New York, C=US correct?
  [no]:  yes


C:\Users\CISC3120\SamplePrograms\Web\05_09\GpaWebApp>
```
where the Eclipse project is at `C:\Users\CISC3120\SamplePrograms\Web\05_09\GpaWebApp>` and 
the keystore password is cisc3120. 

### Configure Spring Boot

Since we use Spring Boot to manage the Spring Framework configuration, we shall edit the
Spring Boot configuration file, i.e., `application.properties` at `src/main/resources` in
the Maven project.

Based on the values we used when we created the key pair, we edit `application.properties`
that should have the following content referring to the key pair we created,
```
server.port: 8443
server.ssl.key-store: keystore.p12
server.ssl.key-store-password: cisc3120
server.ssl.keyStoreType: PKCS12
server.ssl.keyAlias: springframework
```

## Use Spring Boot Developer Tools

The Spring Boot Developer Tools allows us to develop the Spring application without 
restarting the application repeatedly whenever when we makes a change to the application.
The tools will automatically reload the changes. To use the development tools, we simply
add the following to the `pom.xml`, or add the Dev Tool  to the project when we create a 
Spring Boot project in Eclilpse. 
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
```
