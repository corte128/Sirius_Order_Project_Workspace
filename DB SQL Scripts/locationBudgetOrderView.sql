CREATE VIEW location_budget_order_view AS
    SELECT 
        loc.location_id_PK,
        ord.order_id_pk,
        bud.budget_id_pk,
        bud.budget_allotted,
        bud.budget_recommended,
        ord.order_name,
        ord.product_id_fk,
        ord.total_price,
        ord.quantity,
        ord.is_cart
    FROM budget_tbl AS bud
		JOIN location_tbl AS loc
			ON bud.location_id_fk = loc.location_id_pk
		JOIN order_tbl AS ord
			ON bud.order_id_fk = ord.order_id_pk;
            

		