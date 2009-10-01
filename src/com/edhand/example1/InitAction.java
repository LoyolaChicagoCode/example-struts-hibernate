package com.edhand.example1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Processes user's request to add an <code>Item</code> to the database.
 *  
 * @author Konstantin Laufer
 *
 */
public class InitAction extends Action
{

	/**
	 * execute() processes a user's request to add an <code>Item</code> to the database, 
	 * given the data in the AddItemForm.  Forwards the user back to the input page.
	 * 
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
	{
		/*
		 * Initialize the database.
		 */

		ItemService.getInstance().init();
		
		/* 
		 * Always return to the "success" forward, currently
		 * configured in struts-config.xml to return the user
		 * to the AddItem.jsp.
		 */
		return mapping.findForward("success");

	}
}