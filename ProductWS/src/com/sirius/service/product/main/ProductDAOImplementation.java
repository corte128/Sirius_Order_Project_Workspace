package com.sirius.service.product.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import com.sirius.service.product.beans.ProductBean;

public class ProductDAOImplementation 
{
	private static final Logger logger = Logger
			.getLogger(ProductDAOImplementation.class.getName());
	private static final ResourceBundle queries = ResourceBundle
			.getBundle("com.sirius.service.product.properties.queries");
	
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
	public static ProductBean[] getAllProductsByType(int type, Connection conn)
	{
		String query = null;
		PreparedStatement statement = null;
		ResultSet productData = null;
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try
		{
			query = queries.getString("GET_PRODUCTS_BY_TYPE_QUERY");
			statement = conn.prepareStatement(query);
			
			statement.setInt(1, type);
			
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
	
	/**
	 * Gets all products by name
	 * @param name
	 * @param conn
	 */
	public static ProductBean[] getAllProductsByName(String name, Connection conn)
	{
		String query = null;
		PreparedStatement statement = null;
		ResultSet productData = null;
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try
		{
			query = queries.getString("GET_PRODUCTS_BY_NAME_QUERY");
			statement = conn.prepareStatement(query);
			
			if(name == null)
			{
				statement.setString(1, "%");
			}
			else
			{
				statement.setString(1, "%" + name + "%");
			}
			
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
	
	/**
	 * Gets all products by name
	 * @param name
	 * @param conn
	 */
	public static ProductBean[] getAllProductsByNameAndType(String name, int type, Connection conn)
	{
		String query = null;
		PreparedStatement statement = null;
		ResultSet productData = null;
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try
		{
			if(type == 0)
			{
				query = queries.getString("GET_PRODUCTS_BY_NAME_QUERY");
				statement = conn.prepareStatement(query);
			}
			else
			{
				query = queries.getString("GET_PRODUCTS_BY_NAME_AND_TYPE_QUERY");
				statement = conn.prepareStatement(query);
				statement.setInt(2, type);
			}
			
			if(name == null)
			{
				statement.setString(1, "%");
			}
			else
			{
				statement.setString(1, "%" + name + "%");
			}
			
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
