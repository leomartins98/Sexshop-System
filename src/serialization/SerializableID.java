package serialization;
import java.io.Serializable;

public abstract class SerializableID implements Serializable {
    private static Integer nextID = 0;
    protected Integer ID = nextID++;

    public Integer getID() {
        return ID; 
    }
}
