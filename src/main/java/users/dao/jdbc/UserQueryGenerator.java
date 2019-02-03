package users.dao.jdbc;

import users.dao.QueryGenerator;
import users.entity.UserColumns;
import users.entity.UserTable;

import java.lang.reflect.Field;
import java.util.StringJoiner;

public class UserQueryGenerator implements QueryGenerator {
    public String getAll(Class<?> clazz) {
        String tableName = getTableName(clazz);

        StringJoiner stringJoiner = new StringJoiner(", ");

        for (Field declaredField : clazz.getDeclaredFields()) {
            UserColumns columnAnnotation = declaredField.getAnnotation(UserColumns.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        declaredField.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(stringJoiner);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(";");

        return stringBuilder.toString();
    }
    public String getById(Class<?> clazz) {
        String tableName = getTableName(clazz);

        StringJoiner stringJoiner = new StringJoiner(", ");

        for (Field declaredField : clazz.getDeclaredFields()) {
            UserColumns columnAnnotation = declaredField.getAnnotation(UserColumns.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ?
                        declaredField.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        stringBuilder.append(stringJoiner);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(";");

        return stringBuilder.toString();
    }

    private String getTableName(Class<?> clazz){
        UserTable tableAnnotation = clazz.getAnnotation(UserTable.class);

        if (tableAnnotation == null) {
            throw new IllegalArgumentException("@Table is missing");
        }

        return tableAnnotation.name().isEmpty() ? clazz.getName() : tableAnnotation.name();
    }
}
