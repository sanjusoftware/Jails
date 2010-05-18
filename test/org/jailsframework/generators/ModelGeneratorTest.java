package org.jailsframework.generators;

import junit.framework.Assert;
import org.jailsframework.JailsProjectTestBase;
import org.junit.Test;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 4, 2010
 *          Time: 12:08:43 AM
 */

public class ModelGeneratorTest extends JailsProjectTestBase {

    @Test
    public void shouldGenerateNewModelWithTheGivenName() {
        Assert.assertTrue(new ModelGenerator(project).generate("employee"));
        Assert.assertTrue(new File(project.getModelsPath()).listFiles()[0].getName().contains("Employee.java"));
    }
}