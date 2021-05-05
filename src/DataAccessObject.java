import java.util.ArrayList;

public interface DataAccessObject {
    Object getByID(int ID);
    Object deleteByID(int ID);
    void add(Object object);
    ArrayList<Object> getAll();
}