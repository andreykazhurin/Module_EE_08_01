package ua.goit.jsp;

/**
 * Created by Andrey on 14.09.2016.
 */
public class Task {

    private Long id;
    private String name;
    private Category category;
    private Boolean completed;

    private Long currentId = 1L;

    public Task() {
        id = currentId++;
        completed = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task that = (Task) o;

        if (completed != that.completed) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }


    public int compareTo(Task task) {
        return this.getId().compareTo(task.getId());
    }

    @Override
    public String toString() {
        return id + ": " + name +  ": " + category + " [completed: " + completed + "]";
    }


}
