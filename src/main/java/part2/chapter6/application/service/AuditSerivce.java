package part2.chapter6.application.service;

import part2.chapter6.application.provided.AuditDataPersister;
import part2.chapter6.application.provided.AuditManager;
import part2.chapter6.domain.audit.AuditData;
import part2.chapter6.domain.audit.AuditDataUpdate;

import java.time.LocalDateTime;

public record AuditSerivce(
        String directoryName,
        AuditManager auditManager,
        AuditDataPersister auditPersister
) {
    public void addRecord(String visitorName, LocalDateTime visitedDt) {
        AuditData[] auditData = auditPersister.readDirectory(this.directoryName);
        AuditDataUpdate update = auditManager.addRecord(auditData, visitorName, visitedDt);
        auditPersister.applyUpdate(this.directoryName, update);
    }
}
