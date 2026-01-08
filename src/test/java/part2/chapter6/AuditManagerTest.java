package part2.chapter6;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class AuditManagerTest {
    @Test
    void createNewDataWhenMaxEntriesExceeded() {
        // given
        int maxEntriesPerFile = 5;
        var sut = new AuditManager(maxEntriesPerFile);


        var startSeconds = LocalDateTime.of(2023, 1, 1, 0, 0)
                .toEpochSecond(ZoneOffset.of("+09:00"));
        LocalDateTime endDt = LocalDateTime.of(2025, 1, 8, 12, 52);
        var endSeconds = endDt.toEpochSecond(ZoneOffset.of("+09:00"));
        var random = new Random();

        String visitorName = "Jewoo-Sin";
        long randomSeconds;
        LocalDateTime randomDt;
        String newRecord;

        List<String> auditLines1 = new ArrayList<>();
        for (int i = 0; i < maxEntriesPerFile; i++) {
            randomSeconds = random.nextLong(startSeconds, endSeconds);
            randomDt = LocalDateTime.ofEpochSecond(randomSeconds, 0, ZoneOffset.of("+09:00"));
            newRecord = visitorName + ";" + randomDt;
            auditLines1.add(newRecord);
        }

        var files = new AuditData[]{
                new AuditData("audit_1.txt", new ArrayList<>()),
                new AuditData("audit_2.txt", auditLines1)
        };

        // when
        AuditFileUpdate update = sut.addRecord(files, visitorName, endDt);

        // then
        assertThat(update)
                .isEqualTo(
                        new AuditFileUpdate("audit_3.txt", visitorName + ";" + endDt)
                );
    }
}