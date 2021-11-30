package com.bairontapia.projects.cuidamed.utils.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public final class PropertyUtils {

  private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();


  private PropertyUtils() {
    throw new UnsupportedOperationException();
  }

  public static Properties getProperties(final String filePath) throws IOException {
    final Properties properties = new Properties();
    final InputStream inputStream =
        Optional
            .ofNullable(CLASS_LOADER.getResourceAsStream(filePath))
            .orElseThrow(() -> new NullPointerException(
                "File or path to file " + filePath + " does not exist."));
    properties.load(inputStream);
    return (properties);
  }

}
