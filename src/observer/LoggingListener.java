import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggingListener
        implements EventListener {

    private File log;
    private String message;

    public LoggingListener(
            String logFilename,
            String message) {

        this.log = new File(logFilename);
        this.message = message;
    }

    @Override
    public void update(String filename)
            throws IOException {

        System.out.println(
                "LoggingListener -> "
                + Thread.currentThread().getName()
        );

        FileWriter escritor =
                new FileWriter(log, true);

        escritor.write(
                message + ": "
                + filename + "\n"
        );

        escritor.close();
    }
}
