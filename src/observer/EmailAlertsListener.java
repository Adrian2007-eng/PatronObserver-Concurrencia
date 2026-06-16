import java.io.IOException;

public class EmailAlertsListener
        implements EventListener {

    private String email;
    private String message;

    public EmailAlertsListener(
            String email,
            String message) {

        this.email = email;
        this.message = message;
    }

    @Override
    public void update(String filename)
            throws IOException {

        System.out.println(
                "Email enviado a "
                + email
                + " sobre "
                + filename
                + " | "
                + Thread.currentThread().getName()
        );
    }
}
