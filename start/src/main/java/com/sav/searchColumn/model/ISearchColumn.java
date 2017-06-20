package com.sav.searchColumn.model;

public interface ISearchColumn {

	String[][] readFileCSV(String file);
	
	void writeTXT(String file, String searchString, String pathSave);
	
	void writeCSV(String file, String searchString, String pathSave);
	
}
