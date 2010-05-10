package org.jailsframework.database;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 1:22:28 PM
 */
public class JailsRecordTest {
    private Record testRecord;

    @Before
    public void setUp() {
        testRecord = new TestRecord();
    }

    @Test
    public void shouldCreateNewRecordOfTheGivenModel() {
        Assert.assertTrue(testRecord.create());
    }

    @Test
    public void shouldGiveTableNameGivenModel() {
        Assert.assertEquals("test_jails_records", testRecord.getTable());
    }

    private class TestRecord extends Record {

    }
}
