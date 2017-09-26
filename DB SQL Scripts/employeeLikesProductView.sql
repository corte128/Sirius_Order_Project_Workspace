USE office_application_db;
CREATE VIEW employee_likes_product_view AS
    SELECT DISTINCT
        emp.employee_id_pk,
        emp.location_id_fk,
        prod.product_id_pk,
        prod.product_price
    FROM
        employee_tbl AS emp
            JOIN
        likes_tbl AS lik ON emp.employee_id_pk = lik.employee_id_fk
            JOIN
        product_tbl AS prod ON prod.product_id_pk = lik.product_id_fk
    WHERE
        lik.liked = TRUE;