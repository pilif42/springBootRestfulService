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


## Database
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


- TODO:
    - Spring Security:
            - Implement auth different from basic auth

    - Spring Cloud server:
            - configure one.
            - add our props in there and encrypt the relevant ones
            - pull props from there

    - add Spring JPA:
           - add @GeneratedValue(strategy=GenerationType.AUTO) to id on Customer
           - replace findById with findOne in CustomerRepository

    - add Flyway

    - hook up with hadoop: https://spring.io/guides/gs/yarn-basic/

    - hook up with hbase: http://hbase.apache.org/
