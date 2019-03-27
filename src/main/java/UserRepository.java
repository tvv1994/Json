import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserRepository implements Repository {
    Statement stH2;

    public UserRepository(Statement stH2) {
        this.stH2 = stH2;
    }

    @Override
    public void saveListOfUsers(List<User> list){
        for (User u : list) {
            addUser(u);
        }
    }

    @Override
    public void addUser(User u){
        String skill = String.join(", ", u.getSkills());
        String insert = String.format("insert into USERS (name, age, isRabbit, skills) values ('%s', %d, %b, '%s');", u.getName(), u.getAge(), u.isRabbit(), skill);
        try {
            stH2.execute(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readUser(int id) {
        try {
            ResultSet resultSet = stH2.executeQuery(String.format("SELECT * FROM USERS WHERE Id = %d", id));
            resultSet.next();
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            boolean isRabbit = resultSet.getBoolean("isRabbit");
            String skills = resultSet.getString("skills");
            System.out.format("User: %d; name: %s; age: %d; isRabbit: %b; skills: %s.", id, name, age, isRabbit, skills);
        } catch (SQLException e) {
            System.out.println("READ-No data is available.");
        }
    }

    @Override
    public void removeUser(int id){
        try {
            stH2.executeUpdate(String.format("DELETE FROM USERS WHERE Id = %d", id));
        } catch (SQLException e) {
            System.out.println("REMOVE-No data is available.");
        }
    }
}
