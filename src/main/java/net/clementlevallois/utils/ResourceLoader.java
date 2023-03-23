/*
 * Copyright 2017 Clement Levallois
 * http://wwww.clementlevallois.net
 */
package net.clementlevallois.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author LEVALLOIS
 */
public class ResourceLoader {

    static File getResourceFileInSubFolder(String folder, String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        File file = new File(path);
        List<File> files = new ArrayList<>();
        if (file.isDirectory()) {
            try {
                Files.walk(file.toPath()).filter(Files::isRegularFile).forEach(f -> files.add(f.toFile()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            files.add(file);
        }
        for (File fileInFiles : files) {
            if (fileInFiles.getName().equals(fileName)) {
                return fileInFiles;
            }
        }
        return null;
    }

    /**
     *
     * @param resource
     * @return
     */
    public static URL getResource(String resource) {
        final List<ClassLoader> classLoaders = new ArrayList();
        classLoaders.add(Thread.currentThread().getContextClassLoader());
        classLoaders.add(ResourceLoader.class.getClassLoader());

        for (ClassLoader classLoader : classLoaders) {
            final URL url = getResourceWith(classLoader, resource);
            if (url != null) {
                return url;
            }
        }

        final URL systemResource = ClassLoader.getSystemResource(resource);
        if (systemResource != null) {
            return systemResource;
        } else {
            try {
                return new File(resource).toURI().toURL();
            } catch (MalformedURLException e) {
                return null;
            }
        }
    }

    /**
     *
     * @param resource
     * @return
     */
    public static InputStream getResourceAsStream(String resource) {
        final List<ClassLoader> classLoaders = new ArrayList();
        classLoaders.add(Thread.currentThread().getContextClassLoader());
        classLoaders.add(ResourceLoader.class.getClassLoader());

        for (ClassLoader classLoader : classLoaders) {
            final InputStream isr = getResourceAsStreamWith(classLoader, resource);
            if (isr != null) {
                return isr;
            }
        }

        final InputStream systemResource = ClassLoader.getSystemResourceAsStream(resource);
        if (systemResource != null) {
            return systemResource;
        } else {
            return null;
        }
    }

    private static URL getResourceWith(ClassLoader classLoader, String resource) {
        if (classLoader != null) {
            return classLoader.getResource(resource);
        }
        return null;
    }

    private static InputStream getResourceAsStreamWith(ClassLoader classLoader, String resource) {
        if (classLoader != null) {
            return classLoader.getResourceAsStream(resource);
        }
        return null;
    }

    /**
     *
     * @param propertiesFileName
     * @param propertyName
     * @return
     */
    public static String getProperty(String propertiesFileName, String propertyName) {

        try {

            InputStream is = getResourceAsStream(propertiesFileName);

            Properties prop;
            try (InputStreamReader isr = new InputStreamReader(is, "UTF-8")) {
                prop = new Properties();
                // load a properties file
                prop.load(isr);
            }
            // set the properties value
            return prop.getProperty(propertyName);

        } catch (IOException io) {
            System.out.println("properties file not found: " + io.getMessage());
            return "";
        }
    }

    /**
     *
     * @param propertiesFileName
     * @param propertyName
     * @param propertyValue
     */
    public static void setProperty(String propertiesFileName, String propertyName, String propertyValue) {

        try {

//        propertiesFileName = resolveName(propertiesFileName);
            InputStream is = getResourceAsStream(propertiesFileName);

            Properties prop;
            try (InputStreamReader isr = new InputStreamReader(is, "UTF-8")) {
                prop = new Properties();
                prop.load(isr);
            }

            // set the properties value
            prop.setProperty(propertyName, propertyValue);

            OutputStreamWriter char_output = new OutputStreamWriter(
                    new FileOutputStream(propertiesFileName),
                    Charset.forName("UTF-8").newEncoder()
            );

            // save properties to project root folder
//            prop.store(char_output., null);
            is.close();

            char_output.close();

        } catch (IOException io) {
            System.out.println("properties file not found: " + io.getMessage());
        }
    }

    private static String resolveName(String name) {
        if (name == null) {
            return name;
        }
        if (!name.startsWith("/")) {
            Class c = ResourceLoader.class;
            while (c.isArray()) {
                c = c.getComponentType();
            }
            String baseName = c.getName();
            int index = baseName.lastIndexOf('.');
            if (index != -1) {
                name = baseName.substring(0, index).replace('.', '/') + "/" + name;
            }
        } else {
            name = name.substring(1);
        }
        return name;
    }
}
