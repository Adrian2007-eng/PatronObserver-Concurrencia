import java.io.IOException;

public interface EventListener {

    void update(String filename)
            throws IOException;

}
