package com.sav.searchColumn.model;

import java.io.FileNotFoundException;

public interface ISearchColumn {

	String[][] readFileCSV(String file) throws FileNotFoundException, Exception;
	
	void writeTXT(String file, String searchString, String pathSave);
	
	void writeCSV(String file, String searchString, String pathSave);
	
}
