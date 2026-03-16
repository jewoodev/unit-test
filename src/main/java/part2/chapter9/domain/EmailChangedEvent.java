package part2.chapter9.domain;

public class EmailChangedEvent extends UserDomainEvent {
    String newEmail;

    public EmailChangedEvent() {
    }

    public EmailChangedEvent(Long userId, String newEmail) {
        super.userId = userId;
        this.newEmail = newEmail;
    }

    public String getNewEmail() {
        return this.newEmail;
    }
}
