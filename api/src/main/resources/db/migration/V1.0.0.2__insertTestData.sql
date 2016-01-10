-- ------------------------------------------------------------------------------------------------------------
-- Creating initial customers
-- ------------------------------------------------------------------------------------------------------------
INSERT INTO testschema.CUSTOMER (CREATED, CREATED_BY, MODIFIED, MODIFIED_BY, VERSION, FIRSTNAME, LASTNAME) VALUES (1452438934179, '7c13f7e0-0c6d-7c4b-6327-ffde84545db0', 1452438934179, '7c13f7e0-0c6d-7c4b-6327-ffde84545db0', 1, 'Phil','Bross');

INSERT INTO testschema.CUSTOMER (CREATED, CREATED_BY, MODIFIED, MODIFIED_BY, VERSION, FIRSTNAME, LASTNAME) VALUES (1452438934179, '9d13f7e0-0c6d-7c4b-6327-ffde84545db0', 1452438934179, '9d13f7e0-0c6d-7c4b-6327-ffde84545db0', 1, 'John','Doe');

INSERT INTO testschema.CUSTOMER (CREATED, CREATED_BY, MODIFIED, MODIFIED_BY, VERSION, FIRSTNAME, LASTNAME) VALUES (1452438934179, '6e13f7e0-0c6d-7c4b-6327-ffde84545db0', 1452438934179, '6e13f7e0-0c6d-7c4b-6327-ffde84545db0', 1, 'Zinedine','Zidane');

COMMIT;