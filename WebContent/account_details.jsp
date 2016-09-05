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
<%@ page import="java.io.*,java.util.*" %>
<%
    if ((session.getAttribute("userobject") == null) || (session.getAttribute("userobject") == "")) {
%>
You are not logged in<br/>
<a href="index.jsp">Please Login</a>
<%} else {
	User u = (User)session.getAttribute("userobject");
	// Us this for task sync aspect
	// Set refresh, autoload time as 5 seconds
	// response.setIntHeader("Refresh", 5);
	u.updateThisObject(u.userName);
	u = u.getUser();
%>

<form method="post" action="edit_account.jsp">
    <center>
    <table border="1" width="30%" cellpadding="5">
        <thead>
            <tr>
                <th colspan="2">Edit Information Here</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="fname" value="<%=u.fName%>" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lname" value="<%=u.lName%>" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" readonly="readonly" value="<%=u.email%>" /></td>
            </tr>
            <tr>
                <td>User Name</td>
                <td><input type="text" name="uname" readonly="readonly" value="<%=u.userName%>" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save" /></td>
            </tr>
        </tbody>
    </table>
    </center>
</form>
<%
    }
%>

<button onclick="toProfile()">Profile</button>

<script>
function toProfile() {
	window.location = 'profile.jsp';
}
</script>

</body>
</html>