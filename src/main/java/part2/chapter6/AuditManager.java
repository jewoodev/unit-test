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
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(curFilePath.toString()))) {
                    lines.add(newRecord);
                    bw.write(String.join("\n", lines));
                } catch (IOException e) {
                    throw new RuntimeException("존재하던 파일에 내용을 수정하는 도중 에러 발생!", e);
                }
            }
            else {
                int newIndex = filesCnt + 1;
                String newName = "/audit_" + newIndex + ".txt";
                String newFilePath = this.directoryName + newName;
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath))) {
                    bw.write(newRecord);
                } catch (IOException e) {
                    throw new RuntimeException("새 파일을 만드는 도중 에러 발생!", e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("디렉토리 내 선택된 파일 내용을 읽는 도중 에러 발생!", e);
        }
    }
}
