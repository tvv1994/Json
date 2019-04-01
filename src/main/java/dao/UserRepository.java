package dao;

import models.User;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository {
    private Connection connection;
    public static final String CREATE_TABLE = "create table IF NOT EXISTS USERS (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), age INT, isRabbit BOOLEAN, skills VARCHAR(150));";
    private static final String INSERT_SQL = "INSERT INTO users (name, age, isRabbit, skills) values (?,?,?,?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM USERS WHERE id like ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM USERS WHERE id like ?";

    public UserRepository(String db_url){
        try {
            connection = DriverManager.getConnection(db_url);
            try(Statement stH2=connection.createStatement())
            {
                stH2.execute(CREATE_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveList(List list){
        for (Object u : list) {
            add(u);
        }
    }

    @Override
    public void add(Object object){
        try(PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)){
            User u = (User) object;
            stmt.setString(1, u.getName());
            stmt.setInt(2, u.getAge());
            stmt.setBoolean(3, u.isRabbit());
            stmt.setString(4, String.join(", ", u.getSkills()));
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID_SQL)){
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()){
                resultSet.next();
                return Optional.of(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getBoolean(4),
                        Collections.singletonList(resultSet.getString(5))));
            }
        } catch (SQLException e) {
            System.out.println("READ-No data is available.");
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id){
        try(PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("REMOVE-No data is available.");
        }
    }
}
