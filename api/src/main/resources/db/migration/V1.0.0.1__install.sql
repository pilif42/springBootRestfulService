-- ------------------------------------------------------------------------------------------------------------
-- Entity
-- ------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION testschema.update_entity()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.MODIFIED = now ();
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';
COMMENT ON FUNCTION testschema.update_entity () IS 'Performs field updates to core entity types for updates';


CREATE OR REPLACE FUNCTION testschema.insert_entity()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.CREATED = now ();
    NEW.MODIFIED = now ();
    NEW.VERSION = 0;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';
COMMENT ON FUNCTION testschema.insert_entity () IS 'Performs field updates to core entity types for inserts';


CREATE TABLE testschema.ENTITY (
    CREATED  TIMESTAMP NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    MODIFIED TIMESTAMP NOT NULL,
    MODIFIED_BY VARCHAR(50) NOT NULL,
    VERSION  BIGINT    NOT NULL
);
COMMENT ON TABLE testschema.ENTITY IS 'Core entity type to be inherited only.';

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
    LASTNAME VARCHAR(50) NOT NULL
)
    INHERITS (testschema.ENTITY);
COMMENT ON TABLE testschema.CUSTOMER IS 'CUSTOMER stores all customers.';

CREATE TRIGGER TRG_CUSTOMER_UPDATE BEFORE UPDATE ON testschema.CUSTOMER FOR EACH ROW EXECUTE PROCEDURE testschema.update_entity ();
CREATE TRIGGER TRG_CUSTOMER_INSERT BEFORE INSERT ON testschema.CUSTOMER FOR EACH ROW EXECUTE PROCEDURE testschema.insert_entity ();
