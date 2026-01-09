package part2.chapter6.domain.audit;


public class AuditFileUpdate extends AuditDataUpdate {
    private String freshContent;

    public AuditFileUpdate(String fileName, String freshContent) {
        super.name = fileName;
        this.freshContent = freshContent;
    }

    public String getFreshContent() {
        return freshContent;
    }
}
