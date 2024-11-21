package Helpers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;
import java.util.Properties;

public class FetchProperties {

    private static final String rootDir = System.getProperty("user.dir");
    private static final String environmentVariablePath = rootDir+"/src/test/java/Utils/EnvironmentVariables.Properties";
    private static final Properties environmentVariable = new Properties();


private  static void loadProperties(Properties file , String filePath) {
    try {
        InputStream ip = new FileInputStream(filePath);
        file.load(ip);

    } catch (Exception e) {
        throw new RuntimeException(e);

    }
}
static{
    loadProperties(environmentVariable,environmentVariablePath);
}

public static String getEnvironmentVariablePath(String key){
    return environmentVariable.getProperty(key);
}
}
