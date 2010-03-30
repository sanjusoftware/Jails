package org.jailsframework.generators;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: Sanjeev Mishra
 * Date: Mar 30, 2010
 * Time: 11:20:27 PM
 */

public class JailsProjectTest {
    String projectName;
    String path;

    @Before
    public void setUp() {
        path = "E:";
        projectName = "JailsProjectTest";
        deleteProjectStructure(path, projectName);
    }

    @After
    public void tearDown() {
        deleteProjectStructure(path, projectName);
    }

    @Test
    public void shouldGenerateAnMVCJavaProjectOnExecutingJailsCommand() {
        try {
            new JailsProject(path, projectName).generateStructure();
            Assert.assertTrue(true);
        }
        catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    private void deleteProjectStructure(String path, String projectName) {
        new File(path + "\\" + projectName).delete();
    }
}
