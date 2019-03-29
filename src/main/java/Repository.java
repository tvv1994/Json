import java.util.List;

public interface Repository {

    public void  add(Object object);
    public void saveList(List list);
    public void getById(int id);
    public void delete(int id);
}