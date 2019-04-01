package dao;

import java.util.List;
import java.util.Optional;

public interface Repository {

    public void  add(Object object);
    public void saveList(List list);
    public Optional getById(int id);
    public void delete(int id);
}