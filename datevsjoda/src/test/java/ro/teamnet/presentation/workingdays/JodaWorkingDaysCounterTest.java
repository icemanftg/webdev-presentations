package ro.teamnet.presentation.workingdays;

import org.junit.Before;

/**
 * @author Alexandru Burghelea
 * @since 25.09.2012
 */
public class JodaWorkingDaysCounterTest extends GenericWorkingDaysCounterTest {

    @Before
    public void setUp() throws Exception {
        daysCounter = new JodaWorkingDaysCounter();
    }
}
