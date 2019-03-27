import java.util.List;

public interface Repository {

    public void  addUser(User u);
    public void saveListOfUsers(List<User> list);
    public void readUser(int id);
    public void removeUser(int id);
}
