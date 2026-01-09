package part2.chapter6.domain.audit;

import java.util.List;

public record AuditData(String name, List<String> data) {
    public int getDataCount() {
        return data.size();
    }
}
