package com.example.springboot;

public class BuildInfo {
  private final String sha;
  private final String date;
  private final String group;
  private final String name;
  private final String version;

  /**
   * Public constructor
   * @param sha
   * @param date
   * @param group
   * @param name
   * @param version
   */
  public BuildInfo(String sha, String date, String group, String name, String version) {
    this.sha = sha;
    this.date = date;
    this.group = group;
    this.name = name;
    this.version = version;
  }

  public String getSha() {
    return sha;
  }

  public String getDate() {
    return date;
  }

  public String getGroup() {
    return group;
  }

  public String getName() {
    return name;
  }

  public String getFullVersion() {
    return String.format("%s%n%s%n%s:%s:%s", sha, date, group, name, version);
  }
}