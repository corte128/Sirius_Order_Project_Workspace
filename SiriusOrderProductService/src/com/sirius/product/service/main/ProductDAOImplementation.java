package com.sirius.product.service.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.sirius.product.service.beans.ProductBean;

public class ProductDAOImplementation 
{
	private static final Logger logger = Logger
			.getLogger(ProductDAOImplementation.class.getName());
	private static final ResourceBundle queries = ResourceBundle
			.getBundle("com.sirius.product.service.properties.queries");
	
	/**
	 * Gets a product by ID
	 * @param ID
	 */
	public static ProductBean getProductByID(int ID, Connection conn)
	{
		String query = null;
		PreparedStatement statement = null;
		ResultSet productData = null;
		ProductBean product = new ProductBean();
		try
		{
			query = queries.getString("GET_PRODUCT_BY_ID_QUERY");
			statement = conn.prepareStatement(query);
			
			statement.setInt(1, ID);
			
			productData = statement.executeQuery();
			
			
			if(productData.first())
			{
				product.setId(productData.getInt("product_id_pk"));
				product.setName(productData.getString("product_name"));
				product.setType(productData.getString("product_type_name"));
				product.setPrice(productData.getBigDecimal("product_price"));
				product.setDetails(productData.getString("product_details"));
				product.setImage(productData.getString("product_image"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closePreparedStatement(statement);
			DBConnection.closeResultSet(productData);
		}
		return product;
	}
	
	/**
	 * Gets all products by type
	 * @param type
	 * @param conn
	 */
	public static ProductBean[] getAllProductsByType(String type, Connection conn)
	{
		String query = null;
		PreparedStatement statement = null;
		ResultSet productData = null;
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try
		{
			query = queries.getString("GET_PRODUCTS_BY_TYPE_QUERY");
			statement = conn.prepareStatement(query);
			
			statement.setString(1, type);
			
			productData = statement.executeQuery();
			
			
			if(productData.first())
			{
				do
				{
					ProductBean product = new ProductBean();
					
					product.setId(productData.getInt("product_id_pk"));
					product.setName(productData.getString("product_name"));
					product.setType(productData.getString("product_type_name"));
					product.setPrice(productData.getBigDecimal("product_price"));
					product.setDetails(productData.getString("product_details"));
					product.setImage(productData.getString("product_image"));
					
					productList.add(product);
				}
				while(productData.next());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBConnection.closePreparedStatement(statement);
			DBConnection.closeResultSet(productData);
		}
		return productList.toArray(new ProductBean[productList.size()]);
	}
}
