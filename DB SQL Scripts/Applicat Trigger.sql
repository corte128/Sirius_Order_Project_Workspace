USE Office_Application_DB;

delimiter //
CREATE TRIGGER increment_employee_location AFTER INSERT ON employee_tbl
	FOR EACH ROW
    BEGIN
		UPDATE location_tbl
        SET number_of_employees = number_of_employees + 1
        WHERE location_id_pk = NEW.location_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER deincrement_employee_location AFTER UPDATE ON employee_tbl
	FOR EACH ROW
    BEGIN
		IF (OLD.is_employee = TRUE AND NEW.is_employee = FALSE) THEN
			UPDATE location_tbl
			SET number_of_employees = number_of_employees - 1
			WHERE location_id_pk = NEW.location_id_fk;
        END IF;
    END;//
delimiter ;

SELECT * FROM product_type_tbl;
INSERT INTO product_type_tbl(product_type_id_pk, product_type_details, product_type_name, created_by, created_date) 
	VALUES(1, NULL, "Breakroom", 1, (SELECT CURDATE()));
INSERT INTO product_type_tbl(product_type_id_pk, product_type_details, product_type_name, created_by, created_date) 
	VALUES(2, NULL, "Office Supplies", 1, (SELECT CURDATE()));
INSERT INTO product_type_tbl(product_type_id_pk, product_type_details, product_type_name, created_by, created_date) 
	VALUES(3, NULL, "Ink & Toner", 1, (SELECT CURDATE()));