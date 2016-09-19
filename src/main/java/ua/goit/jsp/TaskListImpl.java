package ua.goit.jsp;


import java.util.*;


/**
 * Created by Andrey on 14.09.2016.
 */
public class TaskListImpl implements TaskList {

    private Long currentId = 1L;
    private Map<Long, Task> taskMap = new HashMap<>();

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>(taskMap.values());
        return tasks;
    }

    @Override
    public List<Task> findAllActive() {
        List<Task> activeTasks = new ArrayList<>();

        taskMap.forEach((k,v)->{
            if(!v.isCompleted()){
                activeTasks.add(v);
            }
        });

        return activeTasks;
    }

    @Override
    public List<Task> findAllCompleted() {
        List<Task> completedTasks = new ArrayList<>();

        taskMap.forEach((k,v)->{
            if(v.isCompleted()){
                completedTasks.add(v);
            }
        });

        return completedTasks;
    }

    @Override
    public Task findById(Long id) {
        return taskMap.get(id);
    }

    @Override
    public Long insert(Task task) {
        Long id = currentId++;
        task.setId(id);
        taskMap.putIfAbsent(id, task);
        return id;
    }

    @Override
    public void update(Task task) {
        taskMap.replace(task.getId(), task);
    }

    @Override
    public void delete(Task task) {
        taskMap.remove(task.getId());
    }

    public void updateList(){
        Map<Long, Task> oldTaskMap = taskMap;

        oldTaskMap.forEach((k,v)->{
            if(v.isCompleted()){
                delete(v);
            }
        });

    }

}
