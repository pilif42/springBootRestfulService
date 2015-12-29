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


## Spring Cloud server
step 1 - create a github repo at git@github.com:pilif42/springCloudConfiguration.git

step 2 - encrypt the value of inbound.secured.endpoints.password
            - installed Spring Boot CLI and the Spring Cloud plugin
                        - downloaded spring-boot-cli-1.3.1.RELEASE-bin.zip
                        - set up environment variable SPRING_HOME
                        - open a Command Prompt and spring --version gives Spring CLI v1.3.1.RELEASE
                        - spring install org.springframework.cloud:spring-cloud-cli:1.1.0.BUILD-SNAPSHOT
            - Downloaded from Oracle the Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 8
                        - Copied local_policy.jar and US_export_policy.jar into C:\Program Files\Java\jdk1.8.0_31\jre\lib\security
            - spring encrypt inboundpassword --key @C:\Users\PBrossier\.ssh\mobileBackEnd.pem
                    AQBMhGXzlfEHcaumM/TwF0G9QCKuNGXl4eQ7ibqODNRHjrr1fkYT3KrBmqRFBbv8LC6W0zYI1fguYb2e
                    ytBWCuERrA74n0lb621lz/PeClzk9yhjnwxKFGKM5YpOumo6WjuyDygkcNic8gIo1WyGE1lBuGqwzaW1
                    r92zO9mLSlvOeDgTHWVZluQbjcwbuMdhk7EOVcz7aYams1Z22vMyS/0/E9K+8ijYz/vcmWB//rhGfki3
                    9jva+aRZBV8SWf3aGLmTOHYqZjsfB6g2tLh3Ruuqh1NJePUQTSSxITnTH+/d1JFNT7GBp/wFjhLslmdV
                    I0cAiK8uMjRhxTFshj3MRWk+0vqqj6I2ZdJtGE5hgkleye2j0ZEzBLBiGrae2qpFN8M=
            - amended application.properties at git@github.com:pilif42/springCloudConfiguration.git

- TODO:
    - Spring Cloud server:
            - To run your own server, use the spring-cloud-config-server dependency and @EnableConfigServer.
            - If you set spring.config.name=configserver, the app will run on port 8888 and serve data from a sample repository. You need a spring.cloud.config.server.git.uri to locate the configuration data for your own needs (by default it is the location of a git repository, and can be a local file:.. URL)


    - Spring Security:
            - Implement auth different from basic auth

    - add Spring JPA:
           - add @GeneratedValue(strategy=GenerationType.AUTO) to id on Customer
           - replace findById with findOne in CustomerRepository

    - add Flyway

    - hook up with hadoop: https://spring.io/guides/gs/yarn-basic/

    - hook up with hbase: http://hbase.apache.org/
