package org.jailsframework.generators;

import org.dbmigaret4j.migration.AbstractGenerator;
import org.jailsframework.exceptions.JailsException;
import org.jailsframework.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:sanjusoftware@gmail.com">Sanjeev Mishra</a>
 * @version $Revision: 0.1
 *          Date: May 19, 2010
 *          Time: 1:41:50 AM
 */
public class ModelGenerator extends AbstractGenerator {

    private JailsProject project;

    public ModelGenerator(JailsProject project) {
        this.project = project;
    }

    protected void doGenerate(String modelName) throws Exception {
        File modelFile = new File(project.getModelsPath() + "\\" + modelName + ".java");
        if (!FileUtil.createFile(modelFile)) {
            throw new JailsException("Could not generate org.dbmigaret4j.migration");
        }
        writeContent(modelFile, getSubstitutions(modelName));
    }

    @Override
    protected String getTemplateName() {
        return "model.vm";
    }

    private Map<String, String> getSubstitutions(String modelName) {
        Map<String, String> substitutions = new HashMap<String, String>();
        substitutions.put("modelName", modelName);
        substitutions.put("package", project.getModelPackage());
        return substitutions;
    }

}
