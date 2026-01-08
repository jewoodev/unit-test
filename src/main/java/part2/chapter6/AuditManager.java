package part2.chapter6;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;
    private final AuditRepository repository;

    public AuditManager(int maxEntriesPerFile, String directoryName, AuditRepository repository) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
        this.repository = repository;
    }

    public void addRecord(String visitorName, LocalDateTime visitedDt) {
        List<AuditData> data = this.repository.getAuditData(this.directoryName);
        String newRecord = visitorName + ";" + visitedDt;

        if (data.isEmpty()) {
            repository.createNewAuditData("audit_1.txt", newRecord);
            return;
        }

        AuditData lastData = data.get(data.size() - 1);

        if (lastData.getDataCount() < maxEntriesPerFile) {
            List<String> updatedLines = new ArrayList<>(lastData.data());
            updatedLines.add(newRecord);
            repository.appendToData(lastData.name(), String.join("\n", updatedLines));
        }
        else {
            int newIndex = data.size() + 1;
            String newName = "audit_" + newIndex + ".txt";
            repository.createNewAuditData(newName, newRecord);
        }
    }

    public int getMaxEntriesPerFile() {
        return maxEntriesPerFile;
    }
}
