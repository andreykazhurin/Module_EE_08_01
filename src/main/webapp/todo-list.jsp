<%@ page import="ua.goit.jsp.Category" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%    pageContext.setAttribute("category", Category.values()); %>
<html>
<head>
    <title>HTTP Header Request Example</title>
</head>

<script>
    function onCheckBoxChange(checkBox, taskUuid) {
        var updateTaskForm = document.forms["formUpdateTask"];
        if ( updateTaskForm != null && updateTaskForm != undefined ) {
            var completed = checkBox.checked;
            var tagUuidInput = document.getElementById("updatedItemUuid");
            tagUuidInput.value = taskUuid;
            var updatedItemStateInput = document.getElementById("updatedItemState");
            updatedItemStateInput.value = "" + completed;
            updateTaskForm.submit();
        }
    }
</script>

<body>

<header>My Tasks</header>
<div>
    <h1>My ToDo List</h1>
    <br/><br/>
    <form action="<c:url value="update"/>" method="POST">

        <input type="submit" value="Update Tasks"/>
        <table border="1">
            <tr>
                <th width="70%">Name</th>
                <th width="15%">Category</th>
                <th width="15%">Complete</th>
            </tr>

            <c:forEach items="${taskList}" var="list" varStatus="status">

                <tr>
                    <td>${list.name}</td>
                    <td>${list.category}</td>
                    <td><input type="checkbox" <c:if  test="${list.completed}">checked="checked"</c:if> onchange="onCheckBoxChange(this, '<c:out value="${list.id}"/>');" /></td>
                </tr>

            </c:forEach>

        </table>

    </form>

    <form action="<c:url value="insert"/>" method="POST">
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

    <form id="formUpdateTask" action="<c:url value="toggleStatus"/>" method="post">
        <input type="hidden" id="updatedItemState" name="completed"/>
        <input type="hidden" id="updatedItemUuid" name="id"/>
    </form>

</body>
<html>