package ru.electrictower.tngxb.properties;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * @author Aliaksei Boole
 */
public class TestLinkProp
{
    private String projectName;
    private String planName;
    private String testLinkURL;
    private String devKey;

    public TestLinkProp(String testLinkURL, String devKey)
    {
        checkNotNull(testLinkURL, "testLinkURL");
        checkNotNull(devKey, "devKey");
        this.devKey = devKey;
        this.testLinkURL = testLinkURL;
    }

    public String getPlanName()
    {
        return planName;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        checkNotNull(projectName, "projectName");
        this.projectName = projectName;
    }

    public void setPlanName(String planName)
    {
        checkNotNull(planName, "planName");
        this.planName = planName;
    }

    public String getTestLinkURL()
    {
        return testLinkURL;
    }

    public String getDevKey()
    {
        return devKey;
    }
}
