USE Office_Application_DB;

SELECT * FROM audit_tbl;

delimiter //
CREATE TRIGGER create_audit_record_employee BEFORE INSERT ON employee_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_employee BEFORE UPDATE ON employee_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_product BEFORE INSERT ON product_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_product BEFORE UPDATE ON product_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_location BEFORE INSERT ON location_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_location BEFORE UPDATE ON location_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_visitor BEFORE INSERT ON visitor_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_visitor BEFORE UPDATE ON visitor_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_holiday BEFORE INSERT ON holiday_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_holiday BEFORE UPDATE ON holiday_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_likes BEFORE INSERT ON likes_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_likes BEFORE UPDATE ON likes_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_budget BEFORE INSERT ON budget_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_budget BEFORE UPDATE ON budget_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_login BEFORE INSERT ON login_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_login BEFORE UPDATE ON login_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_order BEFORE INSERT ON order_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_order BEFORE UPDATE ON order_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_employee_type BEFORE INSERT ON employee_type_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_employee_type BEFORE UPDATE ON employee_type_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;

delimiter //
CREATE TRIGGER create_audit_record_product_type BEFORE INSERT ON product_type_tbl
	FOR EACH ROW
    BEGIN
		SET new.audit_id_fk = (SELECT MAX(audit_id_pk) FROM audit_tbl) + 1;
		INSERT INTO audit_tbl(created_date, created_by, updated_date, updated_by)
			VALUES((SELECT CURDATE()), (SELECT CURRENT_USER()), NULL, NULL);
    END;//
delimiter ;

delimiter //
CREATE TRIGGER update_audit_record_product_type BEFORE UPDATE ON product_type_tbl
	FOR EACH ROW
    BEGIN
        UPDATE audit_tbl SET updated_date = (SELECT CURDATE()), updated_by = (SELECT CURRENT_USER())
			WHERE audit_id_pk = new.audit_id_fk;
    END;//
delimiter ;
SELECT CURRENT_USER();