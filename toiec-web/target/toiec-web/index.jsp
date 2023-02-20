<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Page</title>
</head>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>
<body>
<%--<a href="SimpleServlet"></a>--%>

<%
    List<String> studentsList = new ArrayList<>();
    studentsList.add("Student1");
    studentsList.add("Student2");
//    studentsList.add("Student3");
%>
<table>
    <tr>
        <th>Name</th>
    </tr>
    <%
        for(String student: studentsList) {
    %>
    <tr>
        <%--            Cach thu 1--%>
        <%--            <td><%=student%></td>--%>
        <%--            Cac thu 2 (Su dung Impliclit Object), khong can khoi tao instance out tro tu doi tuong PrintWrite van co the goi su dung duoc--%>
        <td><% out.println(student); %></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
