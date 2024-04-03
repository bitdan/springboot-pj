import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.pj.utils.Preconditions.precondition;

/**
 * @version 1.0
 * @description NormalTest
 * @date 2024/4/3 17:23:53
 */
@Slf4j
public class NormalTest {

    @Test
    public void test1() {
        String str = "";
        precondition(str, String::isEmpty, () -> "String cannot be empty");
    }
}
