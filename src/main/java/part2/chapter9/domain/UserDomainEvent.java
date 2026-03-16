package part2.chapter9.domain;

public abstract class UserDomainEvent {
    protected Long userId;
    public Long getUserId() {
        return this.userId;
    }
}
