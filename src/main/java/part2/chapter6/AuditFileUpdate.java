package part2.chapter6;

public record AuditFileUpdate(
        String fileName,
        String freshContent
) {
}
