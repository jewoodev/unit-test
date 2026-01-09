package part2.chapter6.application.provided;

import part2.chapter6.domain.audit.AuditData;
import part2.chapter6.domain.audit.AuditDataUpdate;

public interface AuditDataPersister<T extends AuditDataUpdate> {
    AuditData[] readDirectory(String groupName);
    void applyUpdate(String groupName, T update);
}
