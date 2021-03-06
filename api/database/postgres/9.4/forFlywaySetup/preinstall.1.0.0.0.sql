DROP DATABASE IF EXISTS testdatabase;
DROP USER IF EXISTS testuser;

CREATE ROLE testuser PASSWORD 'testuser' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;

CREATE DATABASE testdatabase WITH OWNER = testuser ENCODING = 'UTF8';

\c testdatabase;

CREATE SCHEMA testschema AUTHORIZATION testuser;

GRANT CONNECT ON DATABASE testdatabase TO testuser;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA testschema TO testuser;
