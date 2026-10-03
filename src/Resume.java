import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    String uuid;

    Resume() {}

    Resume(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            System.out.println("Введён некорректный uuid!");
        } else {
            this.uuid = uuid;
        }
    }
    @Override
    public String toString() {
        return uuid;
    }
}
