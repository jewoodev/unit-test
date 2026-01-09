package part2.chapter6.application.provided;

import part2.chapter6.domain.audit.AuditData;
import part2.chapter6.domain.audit.AuditDataUpdate;

import java.time.LocalDateTime;

public interface AuditManager<R extends AuditDataUpdate, A extends AuditData> {
     R addRecord(A[] record, String name, LocalDateTime createdDt);
}
