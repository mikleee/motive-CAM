package com.aimprosoft.mtkachenko.cam.common.service;

import com.aimprosoft.mtkachenko.cam.common.model.Constraint;
import com.aimprosoft.mtkachenko.cam.common.util.CamLogger;

import java.io.*;
import java.util.List;

/**
 * @author Mikhail Tkachenko
 */
public class TemplateEngine {

    private final static CamLogger LOGGER = CamLogger.getCamLogger(TemplateEngine.class);


    public static final String SPACE = "          ";
    public static final String TEST_CASE_COMMENT = "<!-- TEST CASE SPECIFIC -->";
    public static final String CONTEXT_TAG = "<as:context>";

    public static final String TEMPLATE_EXT = ".cam";


    private String baseTemplatePath;
    private String outputDir;


    public TemplateEngine(String baseTemplatePath, String outputDir) {
        this.baseTemplatePath = baseTemplatePath;
        this.outputDir = outputDir;
    }


    public void createTemplates(List<Constraint> constraints) {
        String baseTemplate = readBaseTemplate();

        for (Constraint constraint : constraints) {
            try {
                saveTestTemplate(constraint, baseTemplate);
            } catch (Exception e) {
                //ignore
            }

        }

    }


    private String readBaseTemplate() {
        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = getReader()) {

            String line;
            while ((line = reader.readLine()) != null) {
                result
                        .append(line)
                        .append('\n');
            }

            LOGGER.trace("Base template [" + baseTemplatePath + "] has been read successfully.");
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return result.toString();
    }


    private void saveTemplate(String templateValue, String templatePath) {
        File file = new File(templatePath);

        if (!file.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
                LOGGER.trace("File [" + templatePath + "] was been created.");
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        try (Writer writer = new FileWriter(file)) {
            writer.write(templateValue);
            LOGGER.trace("Template [" + templatePath + "] was been saved (size = [" + file.length() / 1000. + "] kb).");
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }


    private BufferedReader getReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(new File(baseTemplatePath)));
    }


    private void saveTestTemplate(Constraint constraint, String baseTemplate) {

        String newTemplate = prepareTemplate(constraint, baseTemplate);
        String templatePath = outputDir + "/" + constraint.getName() + TEMPLATE_EXT;

        LOGGER.trace("Try to add constraint " + constraint.getValue() + " into [" + templatePath + "] template.");
        saveTemplate(newTemplate, templatePath);
    }


    private String prepareTemplate(Constraint constraint, String baseTemplate) {

        String constraintValue = CONTEXT_TAG + "\n\n" +
                SPACE + TEST_CASE_COMMENT + "\n\n" +
                SPACE + constraint.getValue() + "\n\n";

        int contextStartIndex = baseTemplate.indexOf(CONTEXT_TAG);
        int contextEndIndex = contextStartIndex + CONTEXT_TAG.length();

        return baseTemplate.substring(0, contextStartIndex) + constraintValue + baseTemplate.substring(contextEndIndex);
    }

}
