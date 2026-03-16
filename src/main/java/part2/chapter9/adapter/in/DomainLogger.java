package part2.chapter9.adapter.in;

import org.slf4j.Logger;
import part2.chapter9.domain.UserType;

public class DomainLogger {
    private final Logger logger;

    public DomainLogger(Logger logger) {
        this.logger = logger;
    }

    public void userTypeHasChanged(Long userId, UserType oldType, UserType freshType) {
        logger.info("User {} has changed from {} to {}", userId, oldType, freshType);
    }
}
