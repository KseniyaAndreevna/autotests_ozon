import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class MaksTest {

    private static List<String> emptyList = Collections.emptyList();

    @Test
    public void test() {
        MatcherAssert.assertThat(emptyList, is(not(empty())));
    }

    @Test
    public void test2() {
        Assert.assertFalse(emptyList.isEmpty());
    }
}
