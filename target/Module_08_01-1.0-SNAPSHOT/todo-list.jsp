<%@ page import="ua.goit.jsp.Category" %>
<%@ page import="ua.goit.jsp.Task" %>
<%@ page import="java.util.Iterator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%    pageContext.setAttribute("category", Category.values()); %>
<html>
<head>
    <title>HTTP Header Request Example</title>
</head>
<body>

<header>My Tasks</header>
<div>
    <h1>My ToDo List</h1>
    <br/><br/>
    <form action="todo-list.jsp" method="post">
        <table border="1">
            <tr>
                <th width="70%">Name</th>
                <th width="15%">Category</th>
                <th width="15%">Complete</th>
            </tr>
            <jsp:useBean id="taskList" class="ua.goit.jsp.TaskListImpl" scope="application"/>
            <%
                Iterator<Task> taskIterator = taskList.findAll().listIterator();
                Task task;
                while (taskIterator.hasNext()) {
                    task = taskIterator.next();
                    String checkboxName = "checkbox" + task.getName();
                    String checkboxValue = request.getParameter(checkboxName);
                    if (checkboxValue != null) {
                        taskIterator.remove();
                    } else {
                        out.print("<tr><td><input type=\"hidden\" name=\"name" + task.getName() + "\" value=\"" + task.getName() + "\">" + task.getName() + "</td>");
                        out.print("<td><input type=\"hidden\" name=\"category" + task.getCategory() + "\" value=\"" + task.getCategory() + "\">" + task.getCategory() + "</td>");
                        if (task.isCompleted()) {
                            out.print("<td><input type=\"checkbox\" name=\"checkbox" + task.getName() + "\" checked=\"checked\"></td>");
                        } else {
                            out.print("<td><input type=\"checkbox\" name=\"checkbox" + task.getName() + "\"></td>");
                        }
                    }
                }
            %>
        </table>
        <input type="submit" value="Update Tasks"/>
    </form>

    <form method="post" action="todo-list.jsp">
          <table>
              <tr>
                  <td>Name:</td>
                  <td><input type="text" name="name"></td>
              </tr>
              <tr>
                  <td>Category:</td>
                  <td>
                      <select name="category">

                          <c:forEach var="entry" items="${category}">
                              <option>${entry.name()}</option>
                          </c:forEach>

                      </select>
                  </td>
              </tr>
              <tr>
                  <td align="right" colspan="2"><input type="submit" value="Submit"></td>
              </tr>
          </table>
    </form>
</body>
<html>