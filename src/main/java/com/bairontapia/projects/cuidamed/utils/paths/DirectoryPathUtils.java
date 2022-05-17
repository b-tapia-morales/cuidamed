package com.bairontapia.projects.cuidamed.utils.paths;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class consists exclusively of static methods that return a {@link String} or a {@link Path}
 * reference to the resources directory or a directory contained within it.
 *
 * <p>String references will terminate on a separator as defined by {@link File#separator} to
 * indicate that they refer to a directory.
 */
public final class DirectoryPathUtils {

  private static final boolean IS_RUNNING_IN_JAR;
  private static final String SEPARATOR;
  private static final String RESOURCES_DIRECTORY;
  private static final String RELATIVE_PATH;
  private static final String ABSOLUTE_PATH;

  static {
    IS_RUNNING_IN_JAR = isRunningInJar();
    SEPARATOR = File.separator;
    RESOURCES_DIRECTORY = System.getProperty("user.dir");
    RELATIVE_PATH = IS_RUNNING_IN_JAR ? "" : "src" + SEPARATOR + "main" + SEPARATOR + "resources";
    ABSOLUTE_PATH = IS_RUNNING_IN_JAR ? "" : RESOURCES_DIRECTORY + SEPARATOR + RELATIVE_PATH;
  }

  private DirectoryPathUtils() {
    throw new UnsupportedOperationException();
  }

  public static boolean isRunningInJar() {
    final var protocol =
        Objects.requireNonNull(DirectoryPathUtils.class.getResource("")).getProtocol();
    return (StringUtils.equals(protocol, "jar"));
  }

  public static boolean isRunningInFileSystem() {
    final var protocol =
        Objects.requireNonNull(DirectoryPathUtils.class.getResource("")).getProtocol();
    return (StringUtils.equals(protocol, "file"));
  }

  public static String relativePathString() {
    return (IS_RUNNING_IN_JAR ? RELATIVE_PATH : RELATIVE_PATH + SEPARATOR);
  }

  public static String absolutePathString() {
    return (IS_RUNNING_IN_JAR ? ABSOLUTE_PATH : ABSOLUTE_PATH + SEPARATOR);
  }

  public static Path relativePath() {
    final String relativePath = relativePathString();
    return (Paths.get(relativePath));
  }

  public static Path absolutePath() {
    final String absolutePath = absolutePathString();
    return (Paths.get(absolutePath));
  }

  public static String relativePathString(final String... folderNames) {
    return (pathBuilder(false, folderNames));
  }

  public static String absolutePathString(final String... folderNames) {
    return (pathBuilder(true, folderNames));
  }

  public static Path relativePath(final String... folderNames) {
    final String relativePath = relativePathString();
    return (Paths.get(relativePath, folderNames));
  }

  public static Path absolutePath(final String... folderNames) {
    final String absolutePath = absolutePathString();
    return (Paths.get(absolutePath, folderNames));
  }

  public static boolean isDirectoryPathValid(final String fullPath) {
    try {
      Paths.get(fullPath);
    } catch (final InvalidPathException exception) {
      return (false);
    }
    return (true);
  }

  public static String pathBuilder(final String... folderNames) {
    if (folderNames.length == 0) {
      return ("");
    }
    final var pathBuilder = new StringBuilder();
    Arrays.stream(folderNames)
        .filter(str -> !str.isEmpty())
        .forEach(str -> pathBuilder.append(str).append(SEPARATOR));
    final var stringPath = pathBuilder.toString();
    assert isDirectoryPathValid(stringPath);
    return (stringPath);
  }

  private static String pathBuilder(final boolean absoluteReference, final String... folderNames) {
    final var string = absoluteReference ? absolutePathString() : relativePathString();
    if (folderNames.length == 0) {
      return (string);
    }
    final var pathBuilder = new StringBuilder(string);
    Arrays.stream(folderNames)
        .filter(str -> !str.isEmpty())
        .forEach(str -> pathBuilder.append(str).append(SEPARATOR));
    final var stringPath = pathBuilder.toString();
    assert isDirectoryPathValid(stringPath);
    return (stringPath);
  }
}
