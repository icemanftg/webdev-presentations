package ro.teamnet.presentation.intuitive;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author Alexandru Burghelea
 * @since 9/21/12
 */

@RunWith(BlockJUnit4ClassRunner.class)
public class HowIntuitiveIsJavaTimeApiTest {
    HowIntuitiveIsJavaTimeApi javaTimeApi;

    @Before
    public void setUp() throws Exception {
        javaTimeApi = new HowIntuitiveIsJavaTimeApi();
    }

    @Test
    public void testFormatedSecondOfFebruary2012() throws Exception {
        /* 21 - Sept - 2012 16:49 LOCAL */
        String actual = javaTimeApi.getSpecificTimeInBruxelles();
        String expected = "21-Sep-2012 15:49";

        Assert.assertEquals("Te-a pacalit Java Date API", expected, actual);
    }
}
