package com.bairontapia.projects.cuidamed.utils.files;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileUtils {

  private FileUtils() {
    throw new UnsupportedOperationException();
  }

  public static boolean exists(final Path path) {
    return (Files.exists(path));
  }

  public static long getSize(final Path path) throws IOException {
    return (Files.size(path));
  }

  public static Optional<Long> getSizeSafe(final Path path) throws IOException {
    return exists(path) ? Optional.of(getSize(path)) : Optional.empty();
  }

  public static boolean isEmpty(final Path path) throws IOException {
    final long fileSize = getSize(path);
    return (fileSize == 0);
  }

  public static Optional<Boolean> isEmptySafe(final Path path) throws IOException {
    return exists(path) ? Optional.of(isEmpty(path)) : Optional.empty();
  }

  public static boolean createIfNotExists(final Path path) throws IOException {
    if (!exists(path)) {
      Files.createFile(path);
      return (true);
    }
    return (false);
  }

  public static boolean deleteIfExists(final Path path) throws IOException {
    if (exists(path)) {
      Files.delete(path);
      return (true);
    }
    return (false);
  }

  public static boolean clearIfExists(final Path path) throws IOException {
    if (exists(path)) {
      Files.newBufferedWriter(path, UTF_8, TRUNCATE_EXISTING);
      return (true);
    }
    return (false);
  }
}
