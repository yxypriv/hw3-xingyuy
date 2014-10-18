package edu.cmu.lti.f14.hw3.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class FileUtil {
	public static interface FileLineProcess {
		public void process(String line);
	}

	public static void iterateFileByLine(String resourcePath, FileLineProcess process) {
		URL resource = FileUtil.class.getClassLoader().getResource(resourcePath);
		String file = resource.getFile();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				process.process(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
