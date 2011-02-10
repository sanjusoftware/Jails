package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.jailsframework.exceptions.JailsException;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:51:30 PM
 */

public class JailsProjectTest extends JailsProjectTestBase {

    @Test
    public void shouldGenerateAnMVCJavaProjectForValidProjectPath() {
        Assert.assertTrue(project.create());
    }

    @Test
    public void shouldGenerateAModelGivenAValidName() {
        Assert.assertTrue("Should have created model \"employee_records\"", project.addModel("employee_records"));
    }

    @Test
    public void shouldGenerateAMigrationGivenAValidName() {
        Assert.assertTrue("Should have created org.dbmigaret4j.migration \"employee\"", project.addMigration("add_employee_table"));
    }

    @Test(expected = JailsException.class)
    public void shouldRaiseInvalidPathExceptionForWrongProjectPath() {
        new JailsProject("InvalidPath", "").create();
    }

}
