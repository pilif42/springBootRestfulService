To start this project, I initially followed http://spring.io/guides/gs/rest-service/ :
		- chose the Maven build option


To run the application as a .jar:
        - option 1:
                - git bash to C:\AndroidStudioProjects\springTutorials\restfulWebService
                - mvn clean package
                - mvn spring-boot:run

        - option 2:
                - build the JAR file with mvn clean package
                - run the JAR by typing: java -jar target/gs-rest-service-0.1.0.jar


To run the application as a .war:
        - see http://spring.io/guides/gs/convert-jar-to-war/


To test the simple controller:
        - default = http://localhost:8080/greeting
        - specific = http://localhost:8080/greeting?name=Joe


To test the controller persisting to the database:
        - default = http://localhost:8080/customer
        - specific = http://localhost:8080/customer?name=Zinedine


- TODO DB work: http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html
        - get it working locally with postgresql
        - create profiles depn on DEV, TEST, etc.: see http://docs.spring.io/spring-boot/docs/current/reference/html/howto-properties-and-configuration.html


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