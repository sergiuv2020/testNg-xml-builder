package ru.electrictower.tngxb;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import org.apache.log4j.Logger;
import ru.electrictower.tngxb.properties.TestLinkProp;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails.FULL;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Aliaksei Boole
 */
class TestLink
{
    private static final Logger LOG = Logger.getLogger(TestLink.class);

    private TestLinkAPI api;
    private TestLinkProp testLinkProp;

    private URL testLinkURL;

    public TestLink(TestLinkProp testLinkProp)
    {
        checkNotNull(testLinkProp, "testLinkProp");
        this.testLinkProp = testLinkProp;
        try
        {
            testLinkURL = new URL(testLinkProp.getTestLinkURL());
        }
        catch (MalformedURLException e)
        {
            processError(e);
        }
    }

    public void connect()
    {
        LOG.info("Started connect to testLink...");
        try
        {
            api = new TestLinkAPI(testLinkURL, testLinkProp.getDevKey());
        }
        catch (TestLinkAPIException e)
        {
            processError(e);
        }
        LOG.info("TestLink say:" + api.ping());
    }

    public List<TestCase> getTestCases()
    {
        List<TestCase> testCasesList = Collections.emptyList();
        try
        {
            Integer projectId = getProjectId();
            Integer testPlanId = getTestPlanId(projectId);

            TestCase[] testCases = api.getTestCasesForTestPlan(testPlanId, null, null, null, null, null, null, null, null, null, FULL);
            testCasesList = Arrays.asList(testCases);
            int count = testCases.length;
            if (count != 0)
            {
                LOG.info("Found " + count + " testCases.");
            }
            else
            {
                LOG.error("TestCases not found.");
                System.exit(-1);
            }
        }
        catch (Exception e)
        {
            processError(e);
        }
        return testCasesList;
    }

    private Integer getProjectId() throws Exception
    {
        String projectName = testLinkProp.getProjectName();
        TestProject[] projects = api.getProjects();
        for (TestProject project : projects)
        {
            if (projectName.equals(project.getName()))
            {
                Integer projectId = project.getId();
                LOG.info("ProjectId[" + projectId + "]");
                return projectId;
            }
        }
        throw new Exception("Project[" + projectName + "] not found.");
    }

    private Integer getTestPlanId(Integer projectId) throws Exception
    {
        String testPlanName = testLinkProp.getPlanName();
        TestPlan[] testPlans = api.getProjectTestPlans(projectId);
        for (TestPlan plan : testPlans)
        {
            if (testPlanName.equals(plan.getName()))
            {
                Integer testPlanId = plan.getId();
                LOG.info("PlanId[" + testPlanId + "]");
                return testPlanId;
            }
        }
        throw new Exception("TestPlan[" + testPlanName + "] not found.");
    }

    private void processError(Throwable e)
    {
        LOG.error(e.getMessage(), e);
        System.exit(-1);
    }

}
