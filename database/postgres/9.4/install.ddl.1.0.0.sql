-- ------------------------------------------------------------------------------------------------------------
-- TODO Remove some access privileges on role teststore using REVOKE
-- ------------------------------------------------------------------------------------------------------------
ALTER ROLE teststore SUPERUSER;

-- ------------------------------------------------------------------------------------------------------------
-- Entity
-- ------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION teststore.update_entity()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.MODIFIED = now ();
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';
COMMENT ON FUNCTION teststore.update_entity () IS 'Performs field updates to core entity types for updates';


CREATE OR REPLACE FUNCTION teststore.insert_entity()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.CREATED = now ();
    NEW.MODIFIED = now ();
    NEW.VERSION = 0;
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';
COMMENT ON FUNCTION teststore.insert_entity () IS 'Performs field updates to core entity types for inserts';


CREATE TABLE teststore.ENTITY (
    CREATED  TIMESTAMP NOT NULL,
    CREATED_BY VARCHAR(50) NOT NULL,
    MODIFIED TIMESTAMP NOT NULL,
    MODIFIED_BY VARCHAR(50) NOT NULL,
    VERSION  BIGINT    NOT NULL
);
COMMENT ON TABLE teststore.ENTITY IS 'Core entity type to be inherited only.';

-- ------------------------------------------------------------------------------------------------------------
-- Customer
-- ------------------------------------------------------------------------------------------------------------
CREATE TABLE teststore.CUSTOMER (
    ID VARCHAR(50) NOT NULL PRIMARY KEY,
    FIRSTNAME VARCHAR(50) NOT NULL,
    LASTNAME VARCHAR(50) NOT NULL
)
    INHERITS (teststore.ENTITY);
COMMENT ON TABLE teststore.CUSTOMER IS 'CUSTOMER stores all customers.';

CREATE TRIGGER TRG_CUSTOMER_UPDATE BEFORE UPDATE ON teststore.CUSTOMER FOR EACH ROW EXECUTE PROCEDURE teststore.update_entity ();
CREATE TRIGGER TRG_CUSTOMER_INSERT BEFORE INSERT ON teststore.CUSTOMER FOR EACH ROW EXECUTE PROCEDURE teststore.insert_entity ();
