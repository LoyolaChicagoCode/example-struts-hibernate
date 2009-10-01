<!-- Copyright 2003 Edward Hand -->

<!-- updated to JSP 1.2 Konstantin Laufer -->

<jsp:root
  xmlns:jsp="http://java.sun.com/JSP/Page"
  xmlns:html="/WEB-INF/struts-html.tld"
  version="1.2">
<jsp:directive.page contentType="text/html"/>
<html:xhtml/>
<html:html locale="true">
<head>
<title>Example 1</title>
<html:base/>
</head>
<body>
<h3>Example 1</h3>
  <table border="0">
    <tr>
      <td>
        <html:link action="/items">Enter Application</html:link>
      </td>
      <td> | </td>
      <td>
        <html:link action="/init">Reinitialize Application (<em>must use first time</em>)</html:link>
      </td>
    </tr>
  </table>
</body>
</html:html>
</jsp:root>