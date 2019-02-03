package users.dao;

public interface QueryGenerator {
    public String getAll(Class<?> clazz);
    public String getById(Class<?> clazz);
}
