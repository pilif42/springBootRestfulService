-- ------------------------------------------------------------------------------------------------------------
-- Customer
-- ------------------------------------------------------------------------------------------------------------
CREATE SEQUENCE CUSTOMERID_SEQ_GEN
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 2147483647
  START 1
  CACHE 10;

GRANT ALL ON ALL SEQUENCES IN SCHEMA testschema TO testuser;

CREATE TABLE testschema.CUSTOMER (
    ID integer NOT NULL PRIMARY KEY DEFAULT nextval('testschema.CUSTOMERID_SEQ_GEN'),
    FIRSTNAME VARCHAR(50) NOT NULL,
    LASTNAME VARCHAR(50) NOT NULL,
    CREATED  bigint NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    MODIFIED bigint NOT NULL,
    MODIFIED_BY VARCHAR(50) NOT NULL,
    VERSION integer NOT NULL
);
COMMENT ON TABLE testschema.CUSTOMER IS 'CUSTOMER stores all customers.';
