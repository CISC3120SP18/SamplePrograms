# Enhanced GPA App
This example program demonstrate the following
* how to use Spring RestController,
* how to use path variables, 
* how to use a persistent storage engine,
* and how to enable TLS (HTTP over SSL/TLS, or HTTPS. )

## Set up MongoDB

MongoDB is a NoSQL database. You can download the community edition
of MongoDB freely at [https://www.mongodb.com/download-center#community](https://www.mongodb.com/download-center#community) and install the
MongoDB by running the downloaded package. When you install MongoDB,
it is recommended that you install the MongoDB Compass Community, a GUI
application to manage MongoDB connections. 

### Configure and Run MongoDB

1. We create a directory for hosting MongoDB database files. Provided 
that you choose the directory as `/Data/MongoDB/db`, you may create
the directory on Mac OS X (or Linux) or on Windows using the command below,
respectively,

```
mkdir -p /Data/MongoDB/db
```

```
mkdir C:\Data\MongoDB\db
```

2. Run the MongoDB server. Assuming the MongoDB is install at the
directory `/Applications/MongoDB` and the version is 3.6, the
bin directory is likely at `/Application/MongoDB/3.6/bin`. We
can run the MongoDB server using the command below on Mac OS X (or Linux)
or on Windows, respectively,

```
/Application/MongoDB/Server/3.6/bin/mongod --dbpath /Data/MongoDB/db
```

```
C:\Applications\MongoDB\Server\3.6\bin\mongod --dbpath /Data/MongoDB/db
```

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
set the search path to include the JDK's bin directory, or use the absolute path of the `keytool`. 
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

