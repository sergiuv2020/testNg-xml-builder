package ru.electrictower.tngxb.properties;

import br.eti.kinoshita.testlinkjavaapi.model.TestCase;

import java.lang.reflect.Method;

/**
 * @author Aliaksei Boole
 */
public interface SelectionLogic
{
    boolean select(Method method, TestCase testCase);
}
