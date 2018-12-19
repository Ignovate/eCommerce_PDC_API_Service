package com.gaia.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtils {

	public static boolean createDirectory(String dir) throws RuntimeException {
		if (Objects.isNull(dir))
			return false;

		Path path = Paths.get(dir);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				return true;
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}

}
