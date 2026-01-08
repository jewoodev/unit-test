package part2.chapter6;

import java.time.LocalDateTime;

public record AuditFileSerivce(
        String directoryName,
        AuditFileManager auditFileManager,
        AuditFilePersister auditFilePersister
) {
    public AuditFileSerivce(String directoryName, int maxEntriesPerFile) {
        this(directoryName, new AuditFileManager(maxEntriesPerFile), new AuditFilePersister());
    }

    public void addRecord(String visitorName, LocalDateTime visitedDt) {
        AuditData[] auditData = auditFilePersister.readDirectory(this.directoryName);
        AuditFileUpdate update = auditFileManager.addRecord(auditData, visitorName, visitedDt);
        auditFilePersister.applyUpdate(this.directoryName, update);
    }
}
