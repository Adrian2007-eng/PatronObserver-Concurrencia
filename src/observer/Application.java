import java.io.IOException;

public class Application {

    public static void main(String[] args)
            throws IOException, InterruptedException {

        System.out.println("Observer con Concurrencia");

        Editor editor = new Editor();

        LoggingListener logger =
                new LoggingListener(
                        "log.txt",
                        "Archivo actualizado"
                );

        EmailAlertsListener emailAlerts =
                new EmailAlertsListener(
                        "rjru@gmail.com",
                        "Se realizó una modificación"
                );

        editor.events.subscribe("open", logger);
        editor.events.subscribe("save", emailAlerts);

        editor.openFile("doc.txt");
        editor.saveFile();

        editor.openFile("doc.txt");
        editor.saveFile();

        Thread.sleep(2000);

        editor.events.shutdown();
    }
}
