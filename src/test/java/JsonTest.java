import com.alibaba.fastjson.JSON;
import com.yuyang.book.pojo.User;
import org.junit.Test;

/**
 * @author yu yang
 * @date 2022/8/31 -22:55
 */
public class JsonTest {
    @Test
    public void testJson(){
        String string1 = JSON.toJSONString("{uname:1}");
        Object o = JSON.toJSON(new User(1));
        System.out.println(o);
        System.out.println(JSON.toJSONString(o));
    }
}
