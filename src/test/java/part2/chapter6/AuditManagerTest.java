package part2.chapter6;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class AuditManagerTest {
    @Test
    void useManager() {
        var directoryName = "/home/jewoo/develp-dir/unit-test/src/test/java/part2/chapter6/temp";
        var manager = new AuditManager(5, directoryName);
        this.useManager10Times(directoryName, manager);
    }

    private void useManager10Times(String directoryName, AuditManager manager) {
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
        manager.addRecord("Jewoo-Sin", LocalDateTime.now());
    }
}