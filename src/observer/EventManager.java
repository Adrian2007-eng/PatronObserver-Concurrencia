import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventManager {

    private HashMap<String, List<EventListener>> listeners =
            new HashMap<>();

    private ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public void subscribe(
            String eventType,
            EventListener listener) {

        listeners
                .computeIfAbsent(
                        eventType,
                        k -> new ArrayList<>())
                .add(listener);
    }

    public void unsubscribe(
            String eventType,
            EventListener listener) {

        List<EventListener> users =
                listeners.get(eventType);

        if (users != null) {
            users.remove(listener);
        }
    }

    public void notify(
            String eventType,
            String data) {

        List<EventListener> users =
                listeners.get(eventType);

        if (users == null) {
            return;
        }

        for (EventListener listener : users) {

            executor.submit(() -> {
                try {

                    System.out.println(
                            "Ejecutando en: "
                            + Thread.currentThread().getName()
                    );

                    listener.update(data);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
