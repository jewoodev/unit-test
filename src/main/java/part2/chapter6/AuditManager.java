package part2.chapter6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AuditManager {
    private final int maxEntriesPerFile;
    private final String directoryName;

    public AuditManager(int maxEntriesPerFile, String directoryName) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
    }

    public void addRecord(String visitorName, LocalDateTime visitedDt) {
        File folder = new File(this.directoryName);
        File[] filesIn = folder.listFiles();
        int filesCnt = filesIn.length;
        String newRecord = visitorName + ";" + visitedDt;

        if (filesCnt == 0) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.directoryName + "/audit_1.txt"))) {
                bw.write(newRecord);
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Path curFilePath = filesIn[filesCnt - 1].toPath();
            List<String> lines = Files.lines(curFilePath).collect(Collectors.toList());

            if (lines.size() < this.maxEntriesPerFile) {
                lines.add("\n" + newRecord);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(curFilePath.toString()))) {
                    bw.write(lines.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                int newIndex = filesCnt;
                String newName = "audit_" + newIndex + ".txt";
                Path newFilePath = curFilePath.resolve(newName);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath.toString()))) {
                    bw.write(newRecord);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
