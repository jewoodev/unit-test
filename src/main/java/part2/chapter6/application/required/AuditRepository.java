package part2.chapter6.application.required;

import part2.chapter6.domain.audit.AuditData;

import java.util.List;

public interface AuditRepository {
    List<AuditData> getAuditData(String groupName);
    void createNewAuditData(String name, String content);
    void appendToData(String name, String content);
}
