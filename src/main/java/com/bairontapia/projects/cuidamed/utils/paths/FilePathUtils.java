package com.bairontapia.projects.cuidamed.utils.paths;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class FilePathUtils {

  private FilePathUtils() {
    throw new UnsupportedOperationException();
  }

  public static String relativePathString(final String fileName) {
    return (DirectoryPathUtils.pathBuilder() + fileName);
  }

  public static String absolutePathString(final String fileName) {
    return (DirectoryPathUtils.absolutePathString() + fileName);
  }

  public static Path relativePath(final String fileName) {
    return (Paths.get(relativePathString(fileName)));
  }

  public static Path absolutePath(final String fileName) {
    return (Paths.get(absolutePathString(fileName)));
  }

  public static String relativePathString(final String fileName, final String... folderNames) {
    return (DirectoryPathUtils.pathBuilder(folderNames) + fileName);
  }

  public static String absolutePathString(final String fileName, final String... folderNames) {
    return (DirectoryPathUtils.absolutePathString(folderNames) + fileName);
  }

  public static Path relativePath(final String fileName, final String... folderNames) {
    final String relativePath = DirectoryPathUtils.pathBuilder(folderNames);
    return (Paths.get(relativePath, fileName));
  }

  public static Path absolutePath(final String fileName, final String... folderNames) {
    final String absolutePath = DirectoryPathUtils.absolutePathString(folderNames);
    return (Paths.get(absolutePath, fileName));
  }
}
