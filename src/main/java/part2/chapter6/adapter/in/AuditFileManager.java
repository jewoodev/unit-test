package part2.chapter6.adapter.in;

import part2.chapter6.application.provided.AuditManager;
import part2.chapter6.domain.audit.AuditData;
import part2.chapter6.domain.audit.AuditFileUpdate;

import java.time.LocalDateTime;
import java.util.List;

public record AuditFileManager(
        int maxEntriesPerFile
)  implements AuditManager<AuditFileUpdate, AuditData> {
    public AuditFileUpdate addRecord(
            AuditData[] auditData,
            String visitorName,
            LocalDateTime visitedDt
    ) {
        String freshRecord = visitorName + ";" + visitedDt;

        if (auditData.length == 0) {
            return new AuditFileUpdate("audit_1.txt", freshRecord);
        }

        AuditData lastAuditData = auditData[auditData.length - 1];
        List<String> dataOfLastData = lastAuditData.data();

        if (lastAuditData.getDataCount() < maxEntriesPerFile) {
            dataOfLastData.add(freshRecord);
            String freshContent = String.join("\n", dataOfLastData);
            return new AuditFileUpdate(lastAuditData.name(), freshContent);
        }
        else {
            int freshIndex = auditData.length + 1;
            String freshName = "audit_" + freshIndex + ".txt";
            return new AuditFileUpdate(freshName, freshRecord);
        }
    }
}
