## To start this project, I initially followed http://spring.io/guides/gs/rest-service/ :
		- chose the Gradle build option


## To build
gradlew clean build


## To run
gradlew :bootRun


## Tests
To test the simple controller:
        - default = http://localhost:8080/greeting
        - specific = http://localhost:8080/greeting?name=Joe


To test the controller talking to the database:
        - default = http://localhost:8080/customer
        - specific = http://localhost:8080/customer?id=2


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


- TODO:
    - TODO in CustomerControllerTests
    - add Hibernate
    - hook up with hadoop: https://spring.io/guides/gs/yarn-basic/
    - hook up with hbase: http://hbase.apache.org/
    - add Flyway
    - add Spring Security to the mix.
