package org.geektimes.configuration.demo;

import org.eclipse.microprofile.config.Config;
import org.geektimes.configuration.microprofile.config.JavaConfig;

import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesDemo {

    public static void main(String[] args) throws BackingStoreException {
//        Preferences userPreferences = Preferences.userRoot();
//        userPreferences.put("my-key", "Hello,World");
//        userPreferences.flush();
//        System.out.println(userPreferences.get("my-key", null));
//        Preferences.systemRoot();

        Map systemProperties = System.getProperties();
        systemProperties.put("application.name","25");

        Config config = new JavaConfig();
        Integer value =  config.getValue("application.name",Integer.class);

        System.out.println("ConfigValue======="+value);
    }
}
