package org.jailsframework.querybuilder;

import junit.framework.Assert;
import org.jailsframework.database.Table;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 10:21:12 PM
 */
public class RenameTest {
    @Test
    public void shouldRenameATableGivenTheNewName() {
        Assert.assertEquals("RENAME TABLE tbl_name TO new_tbl_name",
                new Rename(new Table("tbl_name")).to("new_tbl_name").query());
    }
}
