insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('super@mail.com', 'Super User', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test@test.com', 'Alex Sears', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test2@test.com', 'Scout Martinelli', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test3@test.com', 'Jen Sebastian', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test4@test.com', 'Braden Filler', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test5@test.com', 'Chris Harlow', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test6@test.com', 'Will Cortes', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test7@test.com', 'Andrew Bell', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test8@test.com', 'Cherie Parsons', 1, 1, true, 1, (SELECT CURDATE()));

insert into employee_tbl (employee_email, employee_name, location_id_fk, employee_type_id_fk, is_employee, created_by, created_date) 
values ('test9@test.com', 'Michael Scott', 1, 2, true, 1, (SELECT CURDATE()));