-- ------------------------------------------------------------------------------------------------------------
-- Creating initial customers
-- ------------------------------------------------------------------------------------------------------------
INSERT INTO testschema.CUSTOMER (CREATED_BY, MODIFIED_BY, ID, FIRSTNAME, LASTNAME) VALUES ('7c13f7e0-0c6d-7c4b-6327-ffde84545db0', '7c13f7e0-0c6d-7c4b-6327-ffde84545db0',
                                                  '1', 'Phil','Bross');

INSERT INTO testschema.CUSTOMER (CREATED_BY, MODIFIED_BY, ID, FIRSTNAME, LASTNAME) VALUES ('9d13f7e0-0c6d-7c4b-6327-ffde84545db0', '9d13f7e0-0c6d-7c4b-6327-ffde84545db0',
                                                  '2', 'John','Doe');

INSERT INTO testschema.CUSTOMER (CREATED_BY, MODIFIED_BY, ID, FIRSTNAME, LASTNAME) VALUES ('6e13f7e0-0c6d-7c4b-6327-ffde84545db0', '6e13f7e0-0c6d-7c4b-6327-ffde84545db0',
                                                  '3', 'Zinedine','Zidane');

COMMIT;