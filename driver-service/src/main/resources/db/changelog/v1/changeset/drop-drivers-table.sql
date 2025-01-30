DROP TRIGGER IF EXISTS update_modified_at_trigger ON drivers;

DROP FUNCTION IF EXISTS update_modified_at_column();

drop table drivers;