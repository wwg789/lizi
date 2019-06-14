package cn.lizi.lizi.model.code;

import java.io.File;
import java.util.Map;

/**
 * @author why
 */
public class TemplateGenModel {
    private String templateName;
    Map<String, Object> dataMap;

    public TemplateGenModel(String templateName, Map<String, Object> dataMap) {
        this.templateName = templateName;
        this.dataMap = dataMap;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
