## To build
gradlew clean build
Note that the 1st time around after the refactoring and the addition of directories (smokeytests, etc.), I had to do first: gradle wrapper


## To run
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


- TODO:
    - Cucumber tests:
           - add more tests for other endpoints: do a Post, mahe use of Properties as in User Smokey

    - add Spring JPA:
           - add @GeneratedValue(strategy=GenerationType.AUTO) to id on Customer
           - replace findById with findOne in CustomerRepository
    - add Flyway
    - hook up with hadoop: https://spring.io/guides/gs/yarn-basic/
    - hook up with hbase: http://hbase.apache.org/
    - add Spring Security to the mix.
