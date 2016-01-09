-- ------------------------------------------------------------------------------------------------------------
-- Creating initial customers
-- ------------------------------------------------------------------------------------------------------------
INSERT INTO testschema.CUSTOMER (CREATED_BY, MODIFIED_BY, FIRSTNAME, LASTNAME) VALUES ('7c13f7e0-0c6d-7c4b-6327-ffde84545db0', '7c13f7e0-0c6d-7c4b-6327-ffde84545db0', 'Phil','Bross');

INSERT INTO testschema.CUSTOMER (CREATED_BY, MODIFIED_BY, FIRSTNAME, LASTNAME) VALUES ('9d13f7e0-0c6d-7c4b-6327-ffde84545db0', '9d13f7e0-0c6d-7c4b-6327-ffde84545db0', 'John','Doe');

INSERT INTO testschema.CUSTOMER (CREATED_BY, MODIFIED_BY, FIRSTNAME, LASTNAME) VALUES ('6e13f7e0-0c6d-7c4b-6327-ffde84545db0', '6e13f7e0-0c6d-7c4b-6327-ffde84545db0', 'Zinedine','Zidane');

COMMIT;