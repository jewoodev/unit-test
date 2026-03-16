package part2.chapter9.adapter.in;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import part2.GlobalTestSupport;
import part2.chapter9.adapter.PersistenceConfig;

@ExtendWith(SpringExtension.class)
@Import({PersistenceConfig.class})
public abstract class SpringTestSupport extends GlobalTestSupport {
}
