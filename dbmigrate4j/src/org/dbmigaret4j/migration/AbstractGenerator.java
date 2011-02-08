package org.dbmigaret4j.migration;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.jailsframework.util.StringUtil;

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

    public boolean generate(String componentFileName) {
        try {
            doGenerate(new StringUtil(componentFileName).camelize());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected abstract void doGenerate(String componentFileName) throws Exception;

    protected void writeContent(File file, Map<String, String> substitutions) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        velocityEngine.init();
        Template template;
        try {
            template = velocityEngine.getTemplate(getTemplateName());
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Please make sure that your template file exists in the classpath : " + e);
        }
        FileWriter fileWriter = new FileWriter(file);
        template.merge(new VelocityContext(substitutions), fileWriter);
        fileWriter.flush();
        fileWriter.close();

    }

    protected abstract String getTemplateName();
}
