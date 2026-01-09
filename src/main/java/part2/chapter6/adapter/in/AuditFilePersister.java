package part2.chapter6.adapter.in;

import part2.chapter6.application.provided.AuditDataPersister;
import part2.chapter6.domain.audit.AuditData;
import part2.chapter6.domain.audit.AuditFileUpdate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuditFilePersister implements AuditDataPersister<AuditFileUpdate> {
    public AuditData[] readDirectory(String directoryName) {
        File folder = createDirectoryIfNotExist(directoryName);
        File[] files = folder.listFiles();
        if (files == null) return new AuditData[0];
        else
            return Arrays.stream(files)
                    .map(x -> {
                        var curFilePath = x.toPath();
                        List<String> lines = null;
                        try {
                            lines = Files.lines(curFilePath).collect(Collectors.toList());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return new AuditData(x.toString(), lines);
                    }).toArray(AuditData[]::new);
    }

    public void applyUpdate(String directoryName, AuditFileUpdate update) {
        createDirectoryIfNotExist(directoryName);
        String freshFileName = directoryName + "/" + update.getName();
        try (var bw = new BufferedWriter(new FileWriter(freshFileName))) {
            bw.write(update.getFreshContent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File createDirectoryIfNotExist(String directoryName) {
        File folder = new File(directoryName);
        if (!folder.exists()) folder.mkdir();
        return folder;
    }
}
