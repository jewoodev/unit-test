package part2.chapter6;

import java.util.List;

public interface AuditRepository {
    List<AuditData> getAuditData(String groupName);
    void createNewAuditData(String name, String content);
    void appendToData(String name, String content);
}
