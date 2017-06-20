package com.sav.searchColumn.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the method readFileCSV the interface IDownloader and 2
 * abstract method writeTXT and writeCSV
 * 
 * Задание 2.
 * 
 * Создать консольное приложение для поиска столбцов в csv файле и записи
 * результатов в другой файл.
 * 
 * Входные параметры : -i исходный файл(формата csv). -q строка поиска(Может
 * быть регулярным выражением) -o файл результата(txt или csv).
 * 
 * Программа должна найти столбцы, содержащие значение, удолетворяющее строке
 * поиска и записать их в файл результата. Регистр должен учитываться (Cat, CAT,
 * cat это все разные слова). Входной файл должен быть формата csv. Файл с
 * результатом может быть формата csv или txt. Если пишем в csv — столбцы из
 * первого файла преобразуются в строки. Если в txt — все столбцы объединяются в
 * один большой столбец
 * 
 * @author AlexStrug
 *
 */
public abstract class SearchColumn implements ISearchColumn {

	private final static Logger LOGGER = LoggerFactory.getLogger(SearchColumn.class);

	Model model = new Model();

	/**
	 * This method find necessary word and save into .txt file
	 * 
	 * @param file
	 *            - path to file who have format .csv
	 * @param searchString
	 *            - search string which you need to find in .csv file
	 * @param pathSave
	 *            - path to save file which you need to save and have format
	 *            .txt
	 */
	public abstract void writeTXT(String file, String searchString, String pathSave);

	/**
	 * This method find necessary word and save into .csv file
	 * 
	 * @param file
	 *            - path to file who have format .csv
	 * @param searchString
	 *            - search string which you need to find in .csv file
	 * @param pathSave
	 *            - path to save file which you need to save and have format
	 *            .csv
	 */
	public abstract void writeCSV(String file, String searchString, String pathSave);

	/**
	 * This method readFileCSV find file who have format .csv and read him.
	 * After this method creates 2d array from the read .csv file
	 * 
	 * @param file
	 *            - path to file who have format .csv
	 */
	public String[][] readFileCSV(String file) {

		List<List<String>> list = new ArrayList<List<String>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			LOGGER.error("File not found: " + e.getMessage());
		}

		String line = null;
		try {
			line = br.readLine();
		} catch (Exception e) {
			LOGGER.error("Something exception: ");
			e.printStackTrace();
		}
		String[] headers = line.split(";");
		for (String header : headers) {
			List<String> subList = new ArrayList<String>();
			subList.add(header);
			list.add(subList);
		}
		try {
			while ((line = br.readLine()) != null) {
				String[] elems = line.split(";");
				for (int i = 0; i < elems.length; i++) {
					list.get(i).add(elems[i]);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Something exception: ");
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (Exception e) {
			LOGGER.error("Something exception: ");
			e.printStackTrace();
		}
		model.setRows(list.size());
		model.setCols(list.get(0).size());
		String[][] data = new String[model.getRows()][model.getCols()];
		for (int row = 0; row < model.getRows(); row++) {
			for (int col = 0; col < model.getCols(); col++) {
				data[row][col] = list.get(row).get(col);
			}
		}
		return data;
	}
}
