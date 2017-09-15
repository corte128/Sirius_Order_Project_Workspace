package com.sirius.service.product.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.sirius.service.product.beans.ProductBean;

public class ProductDAO
{
	private static final Logger logger = Logger.getLogger(ProductDAO.class.getName());
	/**
	 * Gets a product by ID
	 * @param ID
	 */
	public static ProductBean getProductByID(int ID)
	{
		Connection conn = null;
		ProductBean output = new ProductBean();
		try
		{
			conn = DBConnection.getConnection();
			output = ProductDAOImplementation.getProductByID(ID, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if (conn != null)
			{
				DBConnection.closeConnection(conn);
			}
		}
		
		return output;
	}
	
	/**
	 * Gets all products by type
	 * @param type
	 */
	public static ProductBean[] getAllProductsByType(String type)
	{
		Connection conn = null;
		ProductBean[] output = null;
		try
		{
			conn = DBConnection.getConnection();
			output = ProductDAOImplementation.getAllProductsByType(type, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if (conn != null)
			{
				DBConnection.closeConnection(conn);
			}
		}
		
		return output;
	}

	/**
	 * Gets all products by type
	 * @param name
	 */
	public static ProductBean[] getAllProductsByName(String name)
	{
		Connection conn = null;
		ProductBean[] output = null;
		try
		{
			conn = DBConnection.getConnection();
			output = ProductDAOImplementation.getAllProductsByName(name, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if (conn != null)
			{
				DBConnection.closeConnection(conn);
			}
		}
		
		return output;
	}
	
	/**
	 * Gets all products by name and type
	 * @param name
	 * @param type
	 */
	public static ProductBean[] getAllProductsByNameAndType(String name, String type)
	{
		Connection conn = null;
		ProductBean[] output = null;
		try
		{
			conn = DBConnection.getConnection();
			output = ProductDAOImplementation.getAllProductsByNameAndType(name, type, conn);
		}
		catch(NamingException e)
		{
			logger.log(Level.SEVERE,"Naming Exception Found: Incorrect naming", e);
		}
		catch(SQLException e)
		{
			logger.log(Level.SEVERE,"SQL Exception Found: Incorrect properties", e);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception Found ", e);
		}
		finally
		{
			if (conn != null)
			{
				DBConnection.closeConnection(conn);
			}
		}
		
		return output;
	}
}
