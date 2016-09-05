<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ page import ="basic.*" %>
<%@ page import ="utils.*" %>
<%
	User us = (User)session.getAttribute("userobject");
    String fname = request.getParameter("fname");
    String lname = request.getParameter("lname");
    //out.print(fname + " Registratio " + lname);
    //out.print(us.email + " Registratio " + us.passHash);
    
    User u = new User(us.email, us.passHash);
    u.setfName(fname);
    u.setlName(lname);
    //out.print(u.fName + " : " + u.lName);
	us.updateDb(u);
	us = us.getUser();
	response.sendRedirect("account_details.jsp");
	session.setAttribute("userid", us);
%>

</body>
</html>