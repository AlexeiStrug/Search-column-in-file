package com.sav.searchColumn.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchWriteInFile extends SearchColumn {

	private final static Logger LOGGER = LoggerFactory.getLogger(SearchWriteInFile.class);

	@Override
	public void writeTXT(String file, String searchString, String pathSave) {

		String[][] dataCSV = readFileCSV(file);
		ArrayList<Integer> findColumn = new ArrayList<Integer>();
		for (int k = 0; k < model.getRows(); k++) {
			for (int j = 0; j < model.getCols(); j++) {
				if (dataCSV[k][j].contains(searchString)) {
					findColumn.add(k);
				}
			}
		}
		if (findColumn.size() > 1) {
			PrintWriter writerTXT = null;
			try {
				writerTXT = new PrintWriter(new File(pathSave));
			} catch (IOException e) {
				LOGGER.error("This path not found or you do not have the access to save file in this place: "
						+ e.getMessage());
			} catch (Exception e) {
				LOGGER.error("Something exception: ");
				e.printStackTrace();
			}

			StringBuilder sbTXT = new StringBuilder();
			int i = 0;
			for (int j = findColumn.get(i); j < model.getRows(); j++) {
				for (int k = 0; k < model.getCols(); k++) {
					System.out.println(dataCSV[j][k]);
					sbTXT.append(dataCSV[j][k] + "\n");
				}
				i++;
			}
			System.out.println("\n Succes find!");
			writerTXT.write(sbTXT.toString());
			writerTXT.close();
		} else {
			System.out.println("Not find column with word: " + searchString);
		}
	}

	@Override
	public void writeCSV(String file, String searchString, String pathSave) {

		String[][] dataCSV = readFileCSV(file);

		ArrayList<Integer> findColumn = new ArrayList<Integer>();
		for (int k = 0; k < model.getRows(); k++) {
			for (int j = 0; j < model.getCols(); j++) {
				if (dataCSV[k][j].contains(searchString)) {
					findColumn.add(k);
				}
			}
		}
		if (findColumn.size() > 1) {
			PrintWriter writerCSV = null;
			try {
				writerCSV = new PrintWriter(new File(pathSave));
			} catch (IOException e) {
				LOGGER.error("This path not found or you do not have the access to save file in this place: "
						+ e.getMessage());
			} catch (Exception e) {
				LOGGER.error("Something exception: ");
				e.printStackTrace();
			}

			StringBuilder sbCSV = new StringBuilder();
			int i = 0;
			for (int j = findColumn.get(i); j < model.getRows(); j++) {
				for (int k = 0; k < model.getCols(); k++) {
					System.out.print(dataCSV[j][k] + " ");
					sbCSV.append(dataCSV[j][k] + ";");
				}
				i++;
				sbCSV.append("\n");
				System.out.println();
			}
			System.out.println("\n Succes find!");
			writerCSV.write(sbCSV.toString());
			writerCSV.close();
		} else {
			System.out.println("Not find column with word: " + searchString);
		}
	}
}
