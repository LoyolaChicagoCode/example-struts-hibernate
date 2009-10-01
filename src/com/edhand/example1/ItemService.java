/*
 * Created on Nov 26, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.edhand.example1;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.ObjectNotFoundException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

/**
 * Provides an interface used to access <code>Item</code> objects from the 
 * database using Hibernate.
 * 
 * Copyright 2003 Edward Hand
 * 
 * @author Edward Hand
 *
 */
/*
 * The ItemService class uses the Singleton design pattern to provide an interface 
 * to the Struts application allowing it to work with Item objects through 
 * the persistence layer.
 * 
 * No Hibernate-related code exists above this layer.  This would allow us, in the
 * future, to switch to some other method of object persistence without making changes
 * to the Struts-related code in the application.
 *  
 */
public class ItemService
{

	private static ItemService instance = null;

	private ItemService()
	{

	}

	/**
	 * getInstance() returns the instance of the <code>ItemService</code> singleton.
	 * 
	 * @return <code>ItemService</code> singleton.
	 */
	public static synchronized ItemService getInstance()
	{
		/*
		 * Creates the Singleton instance, if needed.
		 * 
		 */
		if (instance == null)
		{
			instance = new ItemService();
		}
		return instance;
	}

	/**
	 * getItem() returns <code>Item</code> object from database with given id.  If
	 * the user is not found, will return null.
	 * 
	 * @param id The <code>Long</code> id of desired <code>Item</code> 
	 * @return <code>Item</code> with given id, if it exists.  Otherwise, returns null.
	 */
	public Item getItem(Long id)
	{

		/*
		 * Use the ConnectionFactory to retrieve an open
		 * Hibernate Session.
		 * 
		 */
		Session session = ConnectionFactory.getInstance().getSession();

		try
		{
			/*
			 * Calls the load method on the Hibernate session object
			 * to retrieve the Item with the provided id.
			 */
			return (Item) session.load(Item.class, id);
		}
		/*
		 * If the object is not found, i.e., no Item exists with the
		 * requested id, we want the method to return null rather 
		 * than throwing an Exception.
		 * 
		 */
		catch (ObjectNotFoundException onfe)
		{
			return null;
		}
		catch (HibernateException e)
		{
			/*
			 * All Hibernate Exceptions are transformed into an unchecked
			 * RuntimeException.  This will have the effect of causing the
			 * user's request to return an error.
			 * 
			 */
			System.err.println("Hibernate Exception" + e.getMessage());
			throw new RuntimeException(e);
		}
		/*
		 * Regardless of whether the above processing resulted in an Exception
		 * or proceeded normally, we want to close the Hibernate session.  When
		 * closing the session, we must allow for the possibility of a Hibernate
		 * Exception.
		 * 
		 */
		finally
		{
			if (session != null)
			{
				try
				{
					session.close();
				}
				catch (HibernateException e)
				{
					System.err.println("Hibernate Exception" + e.getMessage());
					throw new RuntimeException(e);
				}

			}
		}

	}

	/** 
	 * updateItem() updates specfied <code>Item</code> through Hibernate.
	 * 
	 * @param item An <code>Item</code> to be updated
	 */
	public void updateItem(Item item)
	{
		/*
		 * Use the ConnectionFactory to retrieve an open
		 * Hibernate Session.
		 * 
		 */
		Session session = ConnectionFactory.getInstance().getSession();
		Transaction tx = null;
		
		try
		{
			/*
			 * Update the state of the item using the Hibernate session's update method.
			 * 
			 * Call the flush method to ensure that the object in saved.
			 * 
			 */
			tx = session.beginTransaction();
			session.update(item);
//			session.flush();
			tx.commit();
		}
		catch (HibernateException e)
		{
			try {
   			if (tx != null) tx.rollback();
			} catch (HibernateException e2) { 
				e = e2;
			}
			System.err.println("Hibernate Exception" + e.getMessage());
			throw new RuntimeException(e);
		}
      /*
       * Regardless of whether the above processing resulted in an Exception
       * or proceeded normally, we want to close the Hibernate session.  When
       * closing the session, we must allow for the possibility of a Hibernate
       * Exception.
       * 
       */
		finally
		{
			if (session != null)
			{
				try
				{
					session.close();
				}
				catch (HibernateException e)
				{
					System.err.println("Hibernate Exception" + e.getMessage());
					throw new RuntimeException(e);
				}

			}
		}

	}

	/**
	 * getItemList() returns list of all <code>Item</code> objects stored in the database. 
	 * 
	 * @return <code>List</code> of <code>Item</code> objects.
	 */
	public List getItemList()
	{
		/*
		 * Use the ConnectionFactory to retrieve an open
		 * Hibernate Session.
		 * 
		 */
		Session session = ConnectionFactory.getInstance().getSession();

		try
		{
			/*
			* Build HQL (Hibernate Query Language) query to retrieve a list
			* of all the items currently stored by Hibernate.
			 */
			Query query =
				session.createQuery(
					"select item from com.edhand.example1.Item item order by item.name");
			return query.list();

		}
		catch (HibernateException e)
		{
			System.err.println("Hibernate Exception" + e.getMessage());
			throw new RuntimeException(e);
		}
		/*
		 * Regardless of whether the above processing resulted in an Exception
		 * or proceeded normally, we want to close the Hibernate session.  When
		 * closing the session, we must allow for the possibility of a Hibernate
		 * Exception.
		 * 
		 */
		finally
		{
			if (session != null)
			{
				try
				{
					session.close();
				}
				catch (HibernateException e)
				{
					System.err.println("Hibernate Exception" + e.getMessage());
					throw new RuntimeException(e);
				}

			}
		}
	}

	/**
	 * addItem() inserts new <code>Item</code> into the database through Hibernate.
	 * 
	 * @param item A new <code>Item</code> to be added.
	 */
	public void addItem(Item item)
	{

		Session session = ConnectionFactory.getInstance().getSession();
		Transaction tx = null;

		try
		{
			tx = session.beginTransaction();
			session.save(item);
//			session.flush();
			tx.commit();
		}
		catch (HibernateException e)
		{
			try {
   			if (tx != null) tx.rollback();
			} catch (HibernateException e2) { 
				e = e2;
			}
			System.err.println("Hibernate Exception" + e.getMessage());
			throw new RuntimeException(e);
		}
		/*
		 * Regardless of whether the above processing resulted in an Exception
		 * or proceeded normally, we want to close the Hibernate session.  When
		 * closing the session, we must allow for the possibility of a Hibernate
		 * Exception.
		 * 
		 */
		finally
		{
			if (session != null)
			{
				try
				{

					session.close();
				}
				catch (HibernateException e)
				{
					System.err.println("Hibernate Exception" + e.getMessage());
					throw new RuntimeException(e);
				}

			}
		}
	}

	public void init() {
		ConnectionFactory.getInstance().createTables();
	}
}
