package org.jailsframework.generators;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 19, 2010
 *          Time: 2:03:48 AM
 */
public abstract class AbstractGenerator {
    JailsProject project;
    protected static final String TEMPLATES_PATH = "\\src\\org\\jailsframework\\generators\\templates";

    public AbstractGenerator(JailsProject project) {
        this.project = project;
    }


    public abstract boolean generate(String ComponentFileName);
}
