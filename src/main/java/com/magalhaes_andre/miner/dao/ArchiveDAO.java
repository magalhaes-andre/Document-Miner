package com.magalhaes_andre.miner.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ArchiveDAO {
	private String inDirectoryPath = System.getenv("HOME") + "/data/in/";
	private String outDirectoryPath = System.getenv("HOME") + "/data/out/";
	private File inFolder = new File(inDirectoryPath);
	private File outFolder = new File(outDirectoryPath);

	public List<File> readFolder(String archiveExtension) {
		List<File> archivesToProcess = new ArrayList<File>();
		List<String> processedArchives = new ArrayList<String>();
		if (outFolder.listFiles().length>0 ) {
			for (File file : outFolder.listFiles()) {
				if (file.toString().contains(".done")) {
					processedArchives.add(file.getName());
				} 
			}
		}
		for (File file : inFolder.listFiles()) {
			if (file.toString().contains(archiveExtension) && !processedArchives.contains(file.getName()+".done")) {
				archivesToProcess.add(file);
			}
		}
		return archivesToProcess;
	}

	public List<String> readFile(File file) {
		List<String> fileLines = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			while (line != null) {
				if (!line.isEmpty() || !line.isEmpty()) {
					fileLines.add(line);
				}
				line = reader.readLine();
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return fileLines;
	}

	public boolean writeReport(String fileName, List<String> content) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outDirectoryPath + fileName))) {
			for (String line : content) {
				writer.write(line);
				writer.newLine();
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage());
		}
		return true;
	}

}
