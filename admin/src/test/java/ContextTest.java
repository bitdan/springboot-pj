import com.pj.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @version 1.0
 * @description 综合测试
 * @date 2024/5/21 13:14:15
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {Application.class})
public class ContextTest {
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test6() {
        RBloomFilter<Object> myfilter = redissonClient.getBloomFilter("myfilter");
        myfilter.tryInit(100,0.01);
        myfilter.add("app");
        myfilter.add("666");
        myfilter.add("八股文");
        boolean contains = myfilter.contains("666");
        log.info("contains is : {}", contains);
        boolean contains2 = myfilter.contains("老王");
        log.info("contains2 is : {}", contains2);
        long count = myfilter.count();
        log.info("count is : {}", count);

    }
}
