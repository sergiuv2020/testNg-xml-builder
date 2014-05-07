package ru.electrictower.tngxb;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Aliaksei Boole
 */
class Reflection
{
    private static final Logger LOG = Logger.getLogger(Reflection.class);

    private Reflection()
    {
    }

    public static List<Class> getClasses(String basePackage) throws IOException
    {
        checkNotNull(basePackage, "basePackage");
        ArrayList<Class> classes = new ArrayList<Class>();
        LOG.info("Start scan package[" + basePackage + "]");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String path = basePackage.replace('.', '/');
        Enumeration<URL> resources = classloader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements())
        {
            URL resource = resources.nextElement();
            String filename = resource.getFile();
            String filenameDecoded = URLDecoder.decode(filename, "UTF-8");
            dirs.add(new File(filenameDecoded));
        }
        for (File directory : dirs)
        {
            classes.addAll(FindClasses(directory, basePackage));
        }
        if (classes.size() != 0)
        {
            LOG.info("Found " + classes.size() + " classes.");
        }
        else
        {
            LOG.info("Classes not found");
            System.exit(-1);
        }
        return classes;
    }

    private static List<Class> FindClasses(File directory, String packagename)
    {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists())
        {
            return classes;
        }
        try
        {
            File[] files = directory.listFiles();
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    assert !file.getName().contains(".");
                    classes.addAll(FindClasses(file, packagename + "." + file.getName()));
                }
                else if (file.getName().endsWith(".class"))
                {
                    if (!file.getName().contains("$"))
                    {
                        classes.add(Class.forName(packagename + '.' + file.getName().replace(".class", "")));
                    }

                }
            }
        }
        catch (Exception e)
        {
            processError(e);
        }
        return classes;
    }

    private static void processError(Throwable e)
    {
        LOG.error(e.getMessage(), e);
        System.exit(-1);
    }
}
