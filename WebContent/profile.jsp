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
    if ((session.getAttribute("userobject") == null) || (session.getAttribute("userobject") == "")) {
%>
You are not logged in<br/>
<a href="index.jsp">Please Login</a>
<%} else {
	
	//session.setAttribute("userobject", u);
	User u = (User)session.getAttribute("userobject");
%>
Welcome <%=u.userName%> !!

<button onclick="logOut()">Log out</button>
<button onclick="editAccount()">Edit account details</button>

<script>
function editAccount() {
	window.location = 'account_details.jsp';
}
function logOut() {
	window.location = 'logout.jsp';
}
</script>

</body>
</html>
   <%
}
%>