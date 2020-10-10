import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MasTest {

    private static List<String> emptyList = Collections.emptyList();

    @Test
    public void test() {
        MatcherAssert.assertThat(emptyList, is(not(empty())));
    }

    @Test
    public void test2() {
        Assert.assertFalse(emptyList.isEmpty());
    }

    @Test
    public void testTest(){
        String text1 = "RIDEX";
        String text2 = "Ridex";
        //assertTrue(text1.contains("RIDEX"));
        //assertTrue(text2.contains("RIDEX"));
        //assertEquals(text1, "Ridex");
        String item = "самокат teach team sensei, черный";
        assertTrue(item.contains("tech team"));
    }
}
