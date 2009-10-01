<!-- Copyright 2003 Edward Hand -->

<!-- updated to JSP 1.2 Konstantin Laufer -->

<jsp:root
  xmlns:jsp="http://java.sun.com/JSP/Page"
  xmlns:bean="/WEB-INF/struts-bean.tld"
  xmlns:html="/WEB-INF/struts-html.tld"
  xmlns:logic="/WEB-INF/struts-logic.tld"
  version="1.2">
<jsp:directive.page contentType="text/html" 
  import="com.edhand.example1.ItemService,java.util.List" />
<html:xhtml/>
<html:html locale="true">
<head>
<title>Example 1</title>
<html:base/>
</head>
<body>

<h3>Example 1</h3>
<html:errors />
<jsp:scriptlet>
   /*
    * This code will generate a list of objects from the
    * database and place a reference to this list in the
    * request object.
    *
    */
	List itemList = ItemService.getInstance().getItemList();
	request.setAttribute("items", itemList);
</jsp:scriptlet>
<p>List of items in <code>item</code> table of database <code>test</code>.</p>
<!-- This code will iterate over the list of items, creating a table
	row for each item. -->
<table border="1">
<logic:iterate id="element" name="items" scope="request" type="com.edhand.example1.Item" >
<tr>
   <td><bean:write name="element" property="name" /></td> 
	<td><bean:write name="element" property="description" /></td>	
</tr>	
</logic:iterate>
</table>	
<p>Sumbit to add an item:</p>

<!-- This form will post the submitted data to the addItem Action Mapping -->	
<html:form action="addItem.do" method="post">
<table border="1">
<tr><td>name:</td><td><html:text property="name" /></td>
<td>description:</td><td><html:text property="description" /></td></tr>
</table><br/>
<html:submit />
</html:form>
</body>
</html:html>
</jsp:root>