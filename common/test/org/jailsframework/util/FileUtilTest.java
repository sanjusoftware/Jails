package org.jailsframework.util;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 9, 2010
 *          Time: 1:56:14 AM
 */
public class FileUtilTest {
    private File validFile;
    private File invalidFile;

    @Before
    public void setUp() {
        validFile = new File("test", "project.txt");
        invalidFile = new File("test123", "project.txt");
    }

    @After
    public void tearDown() {
        validFile.delete();
    }

    @Test
    public void shouldCreateFileForValidPath() {
        Assert.assertTrue(FileUtil.createFile(validFile));
    }

    @Test
    public void shouldCreateDirForInValidPath() {
        Assert.assertTrue(FileUtil.makeDirectory(validFile.getPath()));
    }

    @Test
    public void shouldThrowExceptionForInValidPath() {
        Assert.assertFalse(FileUtil.makeDirectory(invalidFile.getPath()));
    }
}
