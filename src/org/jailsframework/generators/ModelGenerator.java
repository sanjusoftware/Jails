package org.jailsframework.generators;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jailsframework.util.FileUtil;
import org.jailsframework.util.StringUtil;

import java.io.File;
import java.io.FileWriter;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 19, 2010
 *          Time: 1:41:50 AM
 */
public class ModelGenerator {
    private JailsProject project;
    private static final String TEMPLATE = "\\src\\org\\jailsframework\\generators\\templates\\model.vm";

    public ModelGenerator(JailsProject project) {
        this.project = project;
    }

    public boolean generate(String modelName) {
        try {
            String camelizedModelName = new StringUtil(modelName).camelize();
            File modelFile = new File(project.getModelsPath() + "\\" + camelizedModelName + ".java");
            FileUtil.createFile(modelFile);
            writeModelContent(modelFile, camelizedModelName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeModelContent(File modelFile, String modelName) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(TEMPLATE);
        VelocityContext context = new VelocityContext();
        context.put("modelName", modelName);
        context.put("package", project.getModelPackage());
        FileWriter fileWriter = new FileWriter(modelFile);
        template.merge(context, fileWriter);
        fileWriter.flush();
        fileWriter.close();

    }
}
