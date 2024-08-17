import cn.dev33.satoken.secure.BCrypt;
import com.pj.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
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
        String password = "union@123";
        String hashpw = BCrypt.hashpw(password);

        log.info(hashpw);
    }

    @Test
    public void test3() {
        Locale locale = Locale.getDefault();

        log.info(String.valueOf(locale));
    }

    @Test
    public void test4() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 32) {
            sb.append(Integer.toHexString(random.nextInt(16)));
        }
        String output = sb.toString();
        log.info(output);
    }

    public int retNum(int input) {
        int n = 0;
        String str = input + "=";
        List<Integer> numList = new ArrayList<>();

        // 填充小于等于输入的四次方数
        for (int i = 1; i <= Math.sqrt(Math.sqrt(input)); i++) {
            numList.add(i * i * i * i);
        }
        // 从最大的四次方根开始遍历，直到找到四次方根的组合使得它们的和等于输入
        for (int i = numList.size() - 1; i >= 0; i--) {
            if (input - numList.get(i) >= 0) {
                input -= numList.get(i);
                str += ((int) Math.sqrt(Math.sqrt(numList.get(i))) + "~4+");
                n++;
                i++;
            }
            if (input == 0) break;
        }
        // 去掉字符串最后的"+"
        str = str.substring(0, str.length() - 1);
        System.out.println(str);
        return n;
    }

    @Test
    public void test5() {
        int num = retNum(83);
        log.info("num is : {}", num);
    }


}
