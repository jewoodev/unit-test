package part2.chapter6;

import org.junit.jupiter.api.Test;
import part2.MockTestSupport;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuditManagerTest extends MockTestSupport {
    @Test
    void createNewDataWhenMaxEntriesExceeded() {
        // given
        var directoryName = "temp";
        int maxEntriesPerFile = 5;

        var sut = new AuditManager(maxEntriesPerFile, directoryName, super.auditRepository);

        var startDt = LocalDateTime.of(2023, 1, 1, 0, 0);
        var endDt = LocalDateTime.now(clock);
        var random = new Random();

        String visitorName = "Jewoo-Sin";
        long daysBetween = ChronoUnit.DAYS.between(startDt, endDt);
        int randomDays;
        LocalDateTime randomDt;
        String newRecord;

        List<String> auditLines1 = new ArrayList<>();
        for (int i = 0; i < maxEntriesPerFile; i++) {
            randomDays = random.nextInt((int) daysBetween + 1);
            randomDt = startDt.plusDays(randomDays);
            newRecord = visitorName + ";" + randomDt;
            auditLines1.add(newRecord);
        }

        List<String> auditLines2 = new ArrayList<>();
        for (int i = 0; i < maxEntriesPerFile; i++) {
            randomDays = random.nextInt((int) daysBetween + 1);
            randomDt = startDt.plusDays(randomDays);
            newRecord = visitorName + ";" + randomDt;
            auditLines2.add(newRecord);
        }

        List<AuditData> auditDataInRepository = List.of(
                new AuditData("audit_1.txt", auditLines1),
                new AuditData("audit_2.txt", auditLines2)
        );

        when(super.auditRepository.getAuditData(eq(directoryName)))
                .thenReturn(auditDataInRepository);

        // when
        randomDays = random.nextInt((int) daysBetween + 1);
        randomDt = startDt.plusDays(randomDays);
        sut.addRecord(visitorName, randomDt);

        // then
        String newName = "audit_" + (auditDataInRepository.size() + 1) + ".txt";
        newRecord = visitorName + ";" + randomDt;
        verify(super.auditRepository).createNewAuditData(eq(newName), eq(newRecord));
    }
}