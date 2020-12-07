# Spring Boot Starter ACME

A Spring Boot module that is meant to ease the pain of generating a valid SSL Certificate using the Automatic Certificate Management Environment (ACME) protocol.

This project depends on the [acme4j](https://github.com/shred/acme4j) library.

# Fork

Please note that this is a fork from another project at [spring-boot-starter-acme](https://github.com/csueiras/spring-boot-starter-acme). My idea is to develop this project following the spring boot versions that are supported by JHipster starting from version 6.10.5

The version of the project will use the Spring Boot version minimum required by a JHipster version in order to keep an easy track of it. For instance:
1. JHipster version 6.10.5 use Spring Boot 2.2.7.RELEASE then the version of the project is 2.2.7.RELEASE

Once a version of the project becomes obsolete another will be generated and keep a compatible track.

# Dependencies

This module depends on having `openssl` on the `PATH` to convert the certificate to PKCS12 format.

# Maven

This library will be available in Maven central

```
<dependency>
  <groupId>com.github.dbranco</groupId>
  <artifactId>spring-boot-starter-acme</artifactId>
  <version>{VERSION}</version>
</dependency>
```

# Usage

1. Add the module to your `pom.xml` file as a dependency.

2. Build your project. 

2. Deploy it to a target machine and point your domain name to the IP address of that machine. LetsEncrypt validates your ownership of the domain by making a callback to the `http://your-domain/.well-known/acme-challenge/{token}` endpoint exposed by this module.

3. Make sure that your server has `openssl` available on its `$PATH`.

4. To activate `spring-boot-starter-acme` and generate a certificate execute:

```
sudo java -Dserver.port=80 -Dacme.enabled=true -Dacme.domain-name=<YOUR_DOMAIN_NAME> -Dacme.accept-terms-of-service=true -jar mysecureapp-0.0.1-SNAPSHOT.jar
```

5. Check your console for a confirmation that the certificate was successfully generated.

6. Stop your application and configure it to make use of the generated certificate:

```
server.port=443
server.ssl.key-store=keystore.p12
server.ssl.key-store-password=password
server.ssl.keyStoreType=PKCS12
```

# Configuration

| Name                         | Description                                  | Type           | Default Value                   | 
|------------------------------|----------------------------------------------|----------------|---------------------------------|
| acme.enabled                 | Activate the spring-boot-starter-acme module | boolean        | false                           |
| acme.accept-terms-of-service | Accepts the CA's terms of service            | boolean        | false                           |
| acme.domain-name             | The domain name to register the SSL cert for | string         |                                 |
| acme.user-key-file           | The location of the user private key file    | string         | user.key                        |
| acme.domain-key-file         | The location of the domain private key file  | string         | domain.key                      |
| acme.domain-csr-file         | The location of the domain csr file          | string         | domain.csr                      |
| acme.domain-chain-file       | The location of the domain chain file        | string         | domain-chain.crt                |
| acme.key-store-file          | The location of the keystore file            | string         | keystore.p12                    |
| acme.key-store-password      | The keystore password                        | string         | password                        |
| acme.endpoint                | The acme endpoint to generate the cert with  | string         | acme://letsencrypt.org          |

# Endpoints

| CA          | Env        | URL                             |
|-------------|------------|---------------------------------|
| LetsEncrypt | Staging    | acme://letsencrypt.org/staging  |
| LetsEncrypt | Prod       | acme://letsencrypt.org          |


# License

Version 2.0 of the Apache License.
