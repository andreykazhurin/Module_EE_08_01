package ua.goit.jsp;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public class TaskListServlet extends HttpServlet {

    private TaskList taskList = new TaskListImpl();

    public static final String FIND_ALL_SERVLET_PATH = "/all";
    public static final String INDEX_PAGE = "/todo-list.jsp";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        String view = processRequest(servletPath, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    private String processRequest(String servletPath, HttpServletRequest request) {

        if (servletPath.equals(FIND_ALL_SERVLET_PATH)) {
            List<Task> tasks = taskList.findAll();
            request.setAttribute("taskList", tasks);
            return INDEX_PAGE;
        }

        if(servletPath.equals("/insert")) {
            Task task = new Task();
            task.setName(request.getParameter("name"));
            task.setCategory(Category.valueOf(request.getParameter("category")));
            taskList.insert(task);

        }
        else if(servletPath.equals("/update")) {

            List<Task> tasks = taskList.findAll();

            for(Task task : tasks) {
                if(task.isCompleted()) {
                    taskList.delete(task);
                }
            }


        }

        else if(servletPath.equals("/toggleStatus")) {
            Task task = taskList.findById(Long.parseLong(request.getParameter("id")));

            if(task != null) {
                boolean completed = "true".equals(request.getParameter("completed")) ? true : false;
                task.setCompleted(completed);
                taskList.update(task);
            }


        }
        return FIND_ALL_SERVLET_PATH;

    }

}