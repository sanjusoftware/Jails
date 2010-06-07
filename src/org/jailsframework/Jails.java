package org.jailsframework;

import org.jailsframework.generators.JailsProject;

import java.io.File;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: Jun 8, 2010
 *          Time: 12:41:20 AM
 */
public class Jails {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.print("Please provide the name of the project to be generated. e.g \"jails foo\" to generate a project foo");
        }
        System.out.println(new File(".").getAbsolutePath());
        new JailsProject(new File(".").getPath(), args[0]).create();
    }
}
