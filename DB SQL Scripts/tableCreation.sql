CREATE DATABASE office_application_db;
USE office_application_db;

CREATE TABLE product_type_tbl (
    product_type_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Product Type ID',
    product_type_details TEXT COMMENT 'Details given about the product family',
    product_type_name VARCHAR(255) NOT NULL COMMENT 'Name of the product',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED COMMENT 'updater id',
    updated_date DATE COMMENT 'updated date',
    PRIMARY KEY (product_type_id_pk)
);

CREATE TABLE product_tbl (
    product_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Product ID',
    product_name VARCHAR(255) COMMENT 'product name',
    product_type_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: Product type ID',
    product_price DECIMAL(8 , 2 ) UNSIGNED NOT NULL COMMENT 'Product price',
    product_details TEXT COMMENT 'Product details',
    product_image VARCHAR(100) COMMENT 'Image data of the product',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED COMMENT 'updater id',
    updated_date DATE COMMENT 'updated date',
    PRIMARY KEY (product_id_pk),
    FOREIGN KEY (product_type_id_fk)
        REFERENCES product_type_tbl (product_type_id_pk)
);

CREATE TABLE state_tbl (
    state_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: State ID',
    state_name VARCHAR(32) NOT NULL COMMENT 'State name with first letter capital',
    state_abbr VARCHAR(8) COMMENT 'Optional state abbreviation (US 2 cap letters)',
    PRIMARY KEY (state_id_pk)
);

CREATE TABLE location_tbl (
    location_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Location Id',
    location_city VARCHAR(255) NOT NULL COMMENT 'City name',
    state_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: State ID',
    number_of_employees SMALLINT UNSIGNED COMMENT 'Number of employees at that location',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED COMMENT 'updater id',
    updated_date DATE COMMENT 'updated date',
    PRIMARY KEY (location_id_pk),
    FOREIGN KEY (state_id_fk)
        REFERENCES state_tbl (state_id_pk)
);

CREATE TABLE employee_type_tbl (
    employee_type_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Employee Type ID',
    employee_type_role VARCHAR(100) NOT NULL,
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED NOT NULL COMMENT 'updater id',
    updated_date DATE NOT NULL COMMENT 'updated date',
    PRIMARY KEY (employee_type_id_pk)
);

CREATE TABLE employee_tbl (
    employee_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Employee ID',
    employee_email VARCHAR(255) NOT NULL COMMENT 'Employee Email',
    employee_name VARCHAR(100) NOT NULL COMMENT 'Employee full name',
    employee_picture LONGBLOB COMMENT 'Image data of the employee',
    location_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: location',
    employee_type_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'the employee type id',
    is_employee BOOLEAN NOT NULL COMMENT 'determines if the employee is active or not',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED NOT NULL COMMENT 'updater id',
    updated_date DATE NOT NULL COMMENT 'updated date',
    PRIMARY KEY (employee_id_pk),
    FOREIGN KEY (location_id_fk)
        REFERENCES location_tbl (location_id_pk),
    FOREIGN KEY (employee_type_id_fk)
        REFERENCES employee_type_tbl (employee_type_id_pk)
);

CREATE TABLE likes_tbl (
    employee_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: employee ID',
    product_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: product ID',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED COMMENT 'updater id',
    updated_date DATE COMMENT 'updated date',
    FOREIGN KEY (employee_id_fk)
        REFERENCES employee_tbl (employee_id_pk),
    FOREIGN KEY (product_id_fk)
        REFERENCES product_tbl (product_id_pk)
);

CREATE TABLE login_tbl (
    username_pk VARCHAR(100) NOT NULL COMMENT 'PK: Username',
    login_password VARCHAR(255) NOT NULL COMMENT 'password of the user',
    is_valid ENUM('accepted', 'pending', 'declined') NOT NULL,
    employee_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: employee ID',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED NOT NULL COMMENT 'updater id',
    updated_date DATE NOT NULL COMMENT 'updated date',
    PRIMARY KEY (username_pk),
    FOREIGN KEY (employee_id_fk)
        REFERENCES employee_tbl (employee_id_pk)
);

CREATE TABLE attendance_tbl (
    employee_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'employee id of the employee at the office',
    attendance_date DATE NOT NULL COMMENT 'date of attendance',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED NOT NULL COMMENT 'updater id',
    updated_date DATE NOT NULL COMMENT 'updated date',
    PRIMARY KEY (employee_id_fk , attendance_date),
    FOREIGN KEY (employee_id_fk)
        REFERENCES employee_tbl (employee_id_pk)
);

CREATE TABLE holiday_tbl (
    holiday_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Holiday ID',
    holiday_date DATE NOT NULL COMMENT 'date of the holiday',
    holiday_name VARCHAR(100) NOT NULL COMMENT 'holiday name',
    location_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'location of where the holiday takes place',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED NOT NULL COMMENT 'updater id',
    updated_date DATE NOT NULL COMMENT 'updated date',
    PRIMARY KEY (holiday_id_pk),
    FOREIGN KEY (location_id_fk)
        REFERENCES location_tbl (location_id_pk)
);

CREATE TABLE visitor_tbl (
    visitor_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Visitor ID',
    start_date DATE NOT NULL COMMENT 'start date of visit',
    end_date DATE NOT NULL COMMENT 'end date of visit',
    number_of_visitors SMALLINT UNSIGNED NOT NULL COMMENT 'amount of visitors',
    comments TEXT COMMENT 'comments on what is happening and who is visiting',
    location_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'location id of where the event is happening',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED NOT NULL COMMENT 'updater id',
    updated_date DATE NOT NULL COMMENT 'updated date',
    PRIMARY KEY (visitor_id_pk),
    FOREIGN KEY (location_id_fk)
        REFERENCES location_tbl (location_id_pk)
);

CREATE TABLE order_tbl (
    order_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Order ID',
    order_name VARCHAR(100) COMMENT 'order name',
    product_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'FK: Product_ID',
    total_price SMALLINT UNSIGNED NOT NULL COMMENT 'total price of the order',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED COMMENT 'updater id',
    updated_date DATE COMMENT 'updated date',
    PRIMARY KEY (order_id_pk),
    FOREIGN KEY (product_id_fk)
        REFERENCES product_tbl (product_id_pk)
);

CREATE TABLE budget_tbl (
    budget_id_pk SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK: Budget ID',
    order_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'order tied to the budget given',
    budget_date DATE NOT NULL COMMENT 'date the budget was made',
    budget_allotted DECIMAL(8 , 2 ) UNSIGNED NOT NULL COMMENT 'budget that was used',
    budget_recommended DECIMAL(8 , 2 ) UNSIGNED NOT NULL COMMENT 'budget that was recommended',
    location_id_fk SMALLINT UNSIGNED NOT NULL COMMENT 'location of where the budget is',
    created_by SMALLINT UNSIGNED NOT NULL COMMENT 'creator id',
    created_date DATE NOT NULL COMMENT 'created date',
    updated_by SMALLINT UNSIGNED COMMENT 'updater id',
    updated_date DATE COMMENT 'updated date',
    PRIMARY KEY (budget_id_pk),
    FOREIGN KEY (location_id_fk)
        REFERENCES location_tbl (location_id_pk),
    FOREIGN KEY (order_id_fk)
        REFERENCES order_tbl (order_id_pk)
);