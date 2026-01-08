package part2.chapter6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AuditSerivceTest {
    @AfterEach
    void tearDown() {
        var directoryName = "audit";
        try {
            File folder = new File(directoryName);
            if (folder.exists()) {
                File[] files = folder.listFiles();
                for (File file : files) {
                    file.delete();
                }
                folder.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createNewFileWhenDirectoryIsEmpty() {
        var directoryName = "audit";
        var sut = new AuditFileSerivce(directoryName, 3);
        var auditFilePersister = new AuditFilePersister();

        sut.addRecord("Jewoo-Sin", LocalDateTime.of(2025, 1, 8, 15, 14));

        var expectedName = "audit/audit_1.txt";
        var expectedData = "Jewoo-Sin;2025-01-08T15:14";
        assertThat(auditFilePersister.readDirectory(directoryName))
                .containsExactly(new AuditData(expectedName, List.of(expectedData)));
    }
}