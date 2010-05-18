package org.jailsframework.generators;

import org.jailsframework.exceptions.JailsException;
import org.jailsframework.util.FileUtil;
import org.jailsframework.util.StringUtil;

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

    public ModelGenerator(JailsProject project) {
        super(project);
    }

    protected void doGenerate(String modelName) throws Exception {
        String camelizedModelName = new StringUtil(modelName).camelize();
        File modelFile = new File(project.getModelsPath() + "\\" + camelizedModelName + ".java");
        if (!FileUtil.createFile(modelFile)) {
            throw new JailsException("Could not generate migration");
        }
        writeContent(modelFile, getSubstitutions(modelName));
    }

    @Override
    protected String getTemplateName() {
        return "\\model.vm";
    }

    private Map<String, String> getSubstitutions(String modelName) {
        Map<String, String> substitutions = new HashMap<String, String>();
        substitutions.put("modelName", modelName);
        substitutions.put("package", project.getModelPackage());
        return substitutions;
    }

}
