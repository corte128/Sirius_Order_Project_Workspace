USE office_application_db;
CREATE VIEW employee_location_budget_view AS
    SELECT DISTINCT
        loc.location_id_pk,
        emp.employee_id_pk,
        stat.state_id_pk,
        bud.budget_id_pk,
        bud.budget_date,
        loc.location_city,
        stat.state_abbr,
        emp.employee_name,
        emp.employee_type_id_fk,
        emp.employee_email,
        loc.number_of_employees,
        bud.budget_recommended,
        bud.budget_allotted
    FROM
        employee_tbl AS emp
            JOIN
        location_tbl AS loc ON emp.location_id_fk = loc.location_id_pk
            JOIN
        state_tbl AS stat ON loc.state_id_fk = stat.state_id_pk
            JOIN
        budget_tbl AS bud ON bud.location_id_fk = loc.location_id_pk
    WHERE
        emp.employee_type_id_fk = 2;