package part2;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import part2.chapter8.adapter.PersistenceConfig;

@ExtendWith(SpringExtension.class)
@Import({PersistenceConfig.class})
public abstract class SpringTestSupport extends GlobalTestSupport {
}
