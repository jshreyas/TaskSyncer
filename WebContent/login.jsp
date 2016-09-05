<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="basic.*" %>
<%
    String uname = request.getParameter("uname");    
    String pwd = request.getParameter("pass");
    
    User u = new User();
	if(u.verify(uname, pwd)) {
		u = u.getUser();
		session.setAttribute("userobject", u);
		response.sendRedirect("profile.jsp");
	}
    else {
		out.println("Invalid password <a href='index.jsp'>try again</a>");
	}
%>
</body>
</html>