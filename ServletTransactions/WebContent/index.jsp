<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--服务器 管理数据源   jdbc/server是 context.xml里面  对应的name--%>
 <%
    Context initContext = new InitialContext();
	DataSource ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/server");
	Connection conn = ds.getConnection();
    out.print(conn);
%>
</body>
</html>