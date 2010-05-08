package org.jailsframework.database.querybuilder;

import org.jailsframework.database.Table;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 8, 2010
 *          Time: 10:21:12 PM
 */
public class RenameTest {
    @Test
    public void shouldRenameATableGivenTheNewName() {
        assertEquals("RENAME TABLE tbl_name TO new_tbl_name",
                new Rename(new Table("tbl_name")).to("new_tbl_name").build());
    }
}
