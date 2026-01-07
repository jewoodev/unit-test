package part2;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public abstract class GlobalTestSupport {
    protected Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
}
