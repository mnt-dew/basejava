/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    String uuid;

    Resume() {
    }

    Resume(String uuid) {
        if (uuid == null || uuid.isBlank()) throw new IllegalArgumentException("Введён некорректный uuid!");
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
