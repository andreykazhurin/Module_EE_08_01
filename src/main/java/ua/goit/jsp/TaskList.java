package ua.goit.jsp;

import java.util.List;

/**
 * Created by Andrey on 14.09.2016.
 */
public interface TaskList {

    List<Task> findAll();
    List<Task> findAllActive();
    List<Task> findAllCompleted();
    Task findById(Long id);
    Long insert(Task task);
    void update(Task task);
    void delete(Task task);

}
