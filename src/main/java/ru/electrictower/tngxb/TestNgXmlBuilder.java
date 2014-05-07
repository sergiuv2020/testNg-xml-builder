package ru.electrictower.tngxb;

import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import ru.electrictower.tngxb.properties.SelectionLogic;
import ru.electrictower.tngxb.properties.TestLinkProp;
import ru.electrictower.tngxb.properties.XmlBuildProp;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Aliaksei Boole
 */
public class TestNgXmlBuilder
{
    private TestLinkProp testLinkProp;
    private XmlBuildProp xmlBuildProp;
    private SelectionLogic selectionLogic;

    private TestNgXmlBuilder()
    {
    }

    public TestNgXmlBuilder(TestLinkProp testLinkProp, XmlBuildProp xmlBuildProp, SelectionLogic selectionLogic)
    {
        checkNotNull(testLinkProp, "testLinkProp");
        checkNotNull(xmlBuildProp, "xmlBuildProp");
        checkNotNull(selectionLogic, "selectionLogic");
        this.selectionLogic = selectionLogic;
        this.testLinkProp = testLinkProp;
        this.xmlBuildProp = xmlBuildProp;
    }

    public void build()
    {
        List<TestCase> testCases = getTestCases();
        TestNgXml testNgXml = new TestNgXml(xmlBuildProp, selectionLogic);
        testNgXml.build(testCases);
        testNgXml.save();
    }

    private List<TestCase> getTestCases()
    {
        TestLink testLink = new TestLink(testLinkProp);
        testLink.connect();
        return testLink.getTestCases();
    }
}
