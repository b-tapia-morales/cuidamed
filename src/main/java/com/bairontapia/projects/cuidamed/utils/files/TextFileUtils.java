package com.bairontapia.projects.cuidamed.utils.files;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileUtils {

  private TextFileUtils() {
    throw new UnsupportedOperationException();
  }

  public static void writeString(final Path path, final String content) throws IOException {
    Files.writeString(path, content, UTF_8, TRUNCATE_EXISTING);
  }

  public static String readString(final Path path) throws IOException {
    if (!FileUtils.exists(path)) {
      return (null);
    }
    return (Files.readString(path, UTF_8));
  }
}
