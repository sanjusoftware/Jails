package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.jailsframework.exceptions.InvalidPathException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:51:30 PM
 */

public class JailsProjectTest extends JailsProjectTestBase {

    @Before
    public void setUp() {
        project = new JailsProject("test", "jailsproject");
    }

    @Test
    public void shouldGenerateAnMVCJavaProjectForValidProjectPath() {
        boolean b = project.create();
        Assert.assertTrue(b);
        Assert.assertTrue("The versions.properties file is not generated",
                new File(project.getDbPath().concat("\\versions.properties")).exists());
        Assert.assertTrue("The database.properties file is not generated",
                new File(project.getConfigPath().concat("\\database.properties")).exists());
    }

    @Test(expected = InvalidPathException.class)
    public void shouldRaiseInvalidPathExceptionForWrongProjectPath() {
        new JailsProject("InvalidPath", "").create();
    }
}
