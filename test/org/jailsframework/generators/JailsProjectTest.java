package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.exceptions.InvalidPathException;
import org.jailsframework.util.TestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:51:30 PM
 */

public class JailsProjectTest {
    String projectName;
    String path;

    @Before
    public void setUp() {
        path = "test";
        projectName = "JailsProjectTest";
    }

    @After
    public void tearDown() {
        TestHelper.deleteJailsProjectStructure(path, projectName);
    }

    @Test
    public void shouldGenerateAnMVCJavaProjectForValidProjectPath() {
        Assert.assertTrue(new JailsProject(path, projectName).generateStructure());
    }

    @Test(expected = InvalidPathException.class)
    public void shouldRaiseInvalidPathExceptionForWrongProjectPath() {
        new JailsProject("InvalidPath", projectName).generateStructure();
    }
}
