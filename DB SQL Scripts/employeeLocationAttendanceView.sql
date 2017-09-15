CREATE VIEW employee_location_attendance_view AS
    SELECT
        emp.employee_id_pk,
        loc.location_id_pk,
        stat.state_id_pk,
        emp.employee_name,
        emp.employee_email,
        att.attendance_date,
        loc.location_city,
        stat.state_name
    FROM
        location_tbl AS loc
            JOIN
        state_tbl AS stat ON loc.state_id_fk = stat.state_id_pk
            JOIN
        employee_tbl AS emp ON emp.location_id_fk = loc.location_id_pk
            JOIN
        attendance_tbl AS att ON att.employee_id_fk = emp.employee_id_pk;