package ru.electrictower.tngxb.properties;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Aliaksei Boole
 */
public class XmlBuildProp
{
    private String xmlFileName;
    private String suiteName;
    private String testName;
    private String threadCount;
    private String basePackage;

    public XmlBuildProp(String xmlFileName, String basePackage)
    {
        checkNotNull(xmlFileName, "XmlFileName");
        checkNotNull(basePackage, "basePackage");
        this.xmlFileName = xmlFileName;
        this.basePackage = basePackage;
        suiteName = "Generated testLink suite";
        testName = "From Belarus with Love";
        threadCount = "1";
    }

    public String getSuiteName()
    {
        return suiteName;
    }

    public String getTestName()
    {
        return testName;
    }

    public String getXmlFileName()
    {
        return xmlFileName;
    }

    public String getThreadCount()
    {
        return threadCount;
    }

    public String getBasePackage()
    {
        return basePackage;
    }

    public void setXmlFileName(String xmlFileName)
    {
        checkNotNull(xmlFileName, "XmlFileName");
        this.xmlFileName = xmlFileName;
    }

    public void setSuiteName(String suiteName)
    {
        checkNotNull(suiteName, "suiteName");
        this.suiteName = suiteName;
    }

    public void setTestName(String testName)
    {
        checkNotNull(testName, "testName");
        this.testName = testName;
    }

    public void setThreadCount(String threadCount)
    {
        checkNotNull(threadCount, "threadCount");
        this.threadCount = threadCount;
    }

    public void setBasePackage(String basePackage)
    {
        checkNotNull(basePackage, "basePackage");
        this.basePackage = basePackage;
    }
}
