package part2.chapter9.domain;

public class UserTypeChangedEvent extends UserDomainEvent {
    UserType oldType;
    UserType newType;

    public UserTypeChangedEvent() {
    }

    public UserTypeChangedEvent(Long userId, UserType oldType, UserType newType) {
        super.userId = userId;
        this.oldType = oldType;
        this.newType = newType;
    }

    public UserType getOldType() {
        return this.oldType;
    }

    public UserType getNewType() {
        return this.newType;
    }
}
