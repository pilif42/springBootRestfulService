## To start this project, I initially followed http://spring.io/guides/gs/rest-service/ :
		- chose the Gradle build option


## To build
gradlew clean build


## To run
gradlew :bootRun


## Tests
To test the healthcheck:
        - curl http://localhost:8080/healthcheck -v -X GET
        - 200 {"timestamp":1445286696772,"host":"localhost:8080","message":"OK","sha":"@sha@"}

To test the simple controller:
        - curl http://localhost:8080/greeting -v -X GET
            - 200 {"id":1,"content":"Hello, World!"}
        - curl http://localhost:8080/greeting?name=Joe -v -X GET
            - 200 {"id":2,"content":"Hello, Joe!"}

To test the controller talking to the database:
        - curl http://localhost:8080/customer -v -X GET
            - 200 {"id":"1","firstName":"Phil","lastName":"Bross"}
        - curl http://localhost:8080/customer?id=2 -v -X GET
            - 200 {"id":"2","firstName":"John","lastName":"Doe"}


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
    - add Spring JPA:
           - clean the db: make id on Customer a long, sort out column names, etc.
           - add @GeneratedValue(strategy=GenerationType.AUTO) to id on Customer
           - replace findById with findOne in CustomerRepository
    - add Flyway
    - hook up with hadoop: https://spring.io/guides/gs/yarn-basic/
    - hook up with hbase: http://hbase.apache.org/
    - add Spring Security to the mix.
