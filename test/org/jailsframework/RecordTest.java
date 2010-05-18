package org.jailsframework;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 1:22:28 PM
 */
public class RecordTest {
    private IRecord testRecord;

    @Before
    public void setUp() {
        testRecord = new TestRecord();
    }

    @Test
    public void shouldCreateNewRecordOfTheGivenModel() {
        assertTrue(testRecord.create());
//        assertEquals(1, new Select().from("test_record").executeQuery().size());
    }

    private class TestRecord extends Record {

    }
}
