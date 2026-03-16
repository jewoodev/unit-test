package part2.chapter9.adapter.in;

import part2.chapter9.application.required.MessageBus;
import part2.chapter9.domain.EmailChangedEvent;
import part2.chapter9.domain.UserDomainEvent;
import part2.chapter9.domain.UserTypeChangedEvent;

import java.util.List;

public class EventDispatcher {
    private final MessageBus messageBus;
    private final DomainLogger domainLogger;

    public EventDispatcher(MessageBus messageBus, DomainLogger domainLogger) {
        this.messageBus = messageBus;
        this.domainLogger = domainLogger;
    }

    public void dispatch(List<UserDomainEvent> events) {
        for (UserDomainEvent event : events) {
            if (event instanceof EmailChangedEvent e) {
                    messageBus.sendEmailChangedMessage(e.getUserId(), e.getNewEmail());
            } else if (event instanceof UserTypeChangedEvent u) {
                domainLogger.userTypeHasChanged(u.getUserId(), u.getOldType(), u.getNewType());
            }
        }
    }
}
