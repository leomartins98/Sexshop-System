package serialization;
import java.io.Serializable;

public abstract class SerializableID implements Serializable {
    protected Integer ID;

    public Integer getID() {
        return ID; 
    }
}
