import cn.dev33.satoken.secure.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Locale;

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

    @Test
    public void test2() {
        String password = "";
        String hashpw = BCrypt.hashpw(password);

        log.info(hashpw);
    }

    @Test
    public void test3() {
        Locale locale = Locale.getDefault();

        log.info(String.valueOf(locale));
    }

}
