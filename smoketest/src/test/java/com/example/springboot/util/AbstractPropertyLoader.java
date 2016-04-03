package com.example.springboot.util;


import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * This class ensures that only one instance of the application properties is read from the file system, avoiding
 * repetition.
 */
public class AbstractPropertyLoader {

  private static final Properties PROPERTIES = new Properties();

  // Load the properties once. Reads system property 'profile' and defaults to 'local'.
  static {
    for (final String profile : new String[]{"", System.getProperty("cuc.env", "local")}) {
      final String filename = "test" + (profile.isEmpty() ? "" : "-" + profile) + ".properties";
      final URL psource = AbstractPropertyLoader.class.getClassLoader().getResource(filename);

      if (psource == null) {
        throw new ExceptionInInitializerError(String.format("Failed to find property file '%s' in classpath", filename));
      }

      System.out.format("Loading properties from file '%s'.\n", filename);
      final Properties p = new Properties();
      try {
        p.load(psource.openStream());
        PROPERTIES.putAll(p);
      } catch (IOException e) {
        ExceptionInInitializerError ex = new ExceptionInInitializerError("Failed to load test.properties");
        ex.initCause(e);
        throw ex;
      }
    }
    for (Object key : PROPERTIES.keySet()) {
      final String val = PROPERTIES.getProperty((String) key);
      System.out.format("'%s' = '%s'\n", key, val);
    }
  }

  public final String getProperty(final String name) {
    if (!PROPERTIES.containsKey(name)) {
      throw new RuntimeException(String.format("Property '%s' not defined in this environment!", name));
    }
    return PROPERTIES.getProperty(name);
  }

  public final String getProperty(final String name, final String defaultValue) {
    return (PROPERTIES.containsKey(name)) ? PROPERTIES.getProperty(name) : defaultValue;
  }

  /**
   * Constructs the URL of an endpoint from environment specific components gleaned from property files.
   * <p>
   * The name is used to obtain the endpoint path from a property with key "cuc.endpoint.${name}".
   * <p>
   * If the property does not exist then the name will be assumed to be the endpoint.
   * <p>
   * When an endpoint is found as a property the URL is constructed as follows: ${cuc.protocol}://${cuc.server}:${cuc.port}${cuc.endpoint.${name}}
   * <p>
   * When an endpoint is NOT found as a property the URL is constructed as follows: ${cuc.protocol}://${cuc.server}:${cuc.port}/${name}
   *
   * @param name the name of the endpoint, for example ping
   * @return
   */
  public final String getEndpoint(final String name) {
    final StringBuilder url = new StringBuilder();
    url.append(getProperty("cuc.protocol")).append("://").append(getProperty("cuc.server")).append(":").append(getProperty("cuc.port"));
    final String ep = getProperty("cuc.endpoint." + name, name);
    if (!ep.startsWith("/")) {
      url.append("/");
    }
    url.append(ep);
    System.out.format("For endpoint '%s', constructed URL '%s'.\n", name, url.toString());
    return url.toString();
  }
}