package org.jailsframework;

import org.jailsframework.generators.JailsProject;
import org.junit.After;
import org.junit.Before;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Apr 28, 2010
 *          Time: 9:23:46 AM
 */
public class JailsProjectTestBase {
    protected JailsProject project;

    @Before
    public void setUp() {
        project = new JailsProject("test", "jailsproject");
        project.create();
    }

    @After
    public void tearDown() {
//        project.destroy();
    }
}
