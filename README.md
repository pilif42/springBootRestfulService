## To build:
        - gradlew clean build
        - Note that the 1st time around after the refactoring and the addition of directories (smokeytests, etc.), I had to do first:
                - gradle wrapper
                - on Mo's laptop, it is then ./gradlew clean build


## To run:
        - inside IntelliJ:
                - Run --> Edit Configurations... --> + on Application
                - Program arguments = --spring.config.location=./tmp/application.properties
        - from C:\aaaProjects\perso\springTutorials\restfulWebService\api
                - java -jar build/libs/springbootexample-1.0.0.jar
                - java -jar build/libs/springbootexample-1.0.0.jar --spring.config.location=file:./tmp/


## Database (ATTENTION - See below Flyway addition that is now in use)
step 1 - create a user with the following from the command line:
    createuser -U postgres -P teststore
    -- When requested the password is `teststore`

step 2 - create a database
    createdb -U postgres -E UTF8 -O teststore teststore

step 3 - create a schema
    run the content of `<ROOT>/database/<RDBM>/<VERSION>/schema.ddl.<APP-VERSION>.sql`

step 4 - populate the schema
    scripts are located in `<ROOT>/database/<RDBM>/<VERSION>/install.ddl.<APP-VERSION>.sql`

step 5 - insert test data
    scripts are located in `<ROOT>/database/<RDBM>/<VERSION>/testdata.<APP-VERSION>.sql`


## Securing the API - Basic Authentication on ALL endpoints
step 1  - in Gradle, add the dependency compile("org.springframework.boot:spring-boot-starter-security")

step 2  - in application.properties, add security.user.name and security.user.password

step 3  - restart the app and now basic authentication is required on all endpoints.
        - Note that we did not have to touch the Unit tests. Still green barring.
        - But, cucumber tests were failing.


## Securing the API - Only a specific endpoint (ie POST to /greeting)
step 1 - create the class SecurityConfiguration

step 2 - add in GreetingController @Secured(SecurityConfiguration.INBOUND_SECURED_ENDPOINTS_ROLE)

step 3  - restart the app and now basic authentication is required on POST to /greeting.


## Spring Cloud server
step 1 - create a github repo at git@github.com:pilif42/springCloudConfiguration.git

step 2 - encrypt the value of inbound.secured.endpoints.password
            - installed Spring Boot CLI and the Spring Cloud plugin
                        - downloaded spring-boot-cli-1.3.1.RELEASE-bin.zip
                        - set up environment variable SPRING_HOME
                        - open a Command Prompt and spring --version gives Spring CLI v1.3.1.RELEASE
                        - spring install org.springframework.cloud:spring-cloud-cli:1.1.0.BUILD-SNAPSHOT
            - downloaded from Oracle the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 8
                        - Copied local_policy.jar and US_export_policy.jar into C:\Program Files\Java\jdk1.8.0_31\jre\lib\security
            - set up with symmetric key:
                    - spring encrypt inboundpassword --key maclestephanoise
                    - result = 17c8a295f053a849d5f1d679756762bbcb826f804c8661d81c7c3f6e5fe425b4
                    - amend application.properties at git@github.com:pilif42/springCloudConfiguration.git
            - TODO set up with asymmetric key:
                    - see issue in README of https://github.com/pilif42/springCloudConfigServer

step 3 - set up a standalone Spring Cloud Config Server
            - see https://github.com/pilif42/springCloudConfigServer

step 4 - read props from the Spring Cloud Config Server
            - add dependency on artifactId spring-cloud-config-client
            - before: program arguments = --spring.config.location=./tmp/application.properties
            - after: program arguments = --spring.cloud.config.uri=http://localhost:8888 --spring.cloud.config.failfast=true --spring.application.name=restfulwebservice --spring.profiles.active=local --spring.cloud.config.label=master --spring.cloud.config.username=user --spring.cloud.config.password=thepassw0rd


## Flyway addition
step 1 - add dependency
            - compile("org.flywaydb:flyway-core")

step 2 - define Flyway props
            - see application.properties under /tmp
            - comment out datasource.url, datasource.username, datasource.password
            - see 'Start of section added for Flyway'

step 3 - define a DataSource correctly
            - neutralise DataConfiguration, ie comment out all

step 4 - prerequisite work on database (this step will have to be repeated if Flyway gets in a mess, ie someone edits a flyway migration file after it has been applied to the database. The simplest solution in DEV is to wipe out the database, etc. and start from scratch with the below.)
            - All progs > Postgres > SQL Shell
            - Execute all in preinstall.1.0.0.0.sql

step 5 - create migration scripts
            - add directory /db/migration under resources
            - add files following naming conventions

step 6 - start the app and verify Flyway scripts are applied correctly


## Json serialization / deserialization
If you want to make it asymmetrical, that is to exclude a Java object property only during deserialization (json String to Object), but instead to include its value during serialization (Object to json String), you must use an appropriate combination of @JsonIgnore and @JsonProperty annotations. To be precise, you must:
    - annotate with @JsonIgnore the property itself
    - annotate with @JsonIgnore its set method
    - annotate with @JsonProperty its get method
Note that the above was taken from 'http://www.davismol.net/2015/03/21/jackson-using-jsonignore-and-jsonproperty-annotations-to-exclude-a-property-only-from-json-deserialization/' and I did not test it.


- TODO:
    - extend Customer and store in db

    - Spring Security:
            - Implement auth different from basic auth

    - hook up with hadoop: https://spring.io/guides/gs/yarn-basic/

    - hook up with hbase: http://hbase.apache.org/
