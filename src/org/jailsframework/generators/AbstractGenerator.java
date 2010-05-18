package org.jailsframework.generators;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

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

    protected void writeContent(File file, Map<String, String> substitutions) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(TEMPLATES_PATH + getTemplateName());
        VelocityContext context = new VelocityContext(substitutions);
        FileWriter fileWriter = new FileWriter(file);
        template.merge(context, fileWriter);
        fileWriter.flush();
        fileWriter.close();

    }

    protected abstract String getTemplateName();
}
