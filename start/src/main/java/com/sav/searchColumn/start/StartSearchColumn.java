package com.sav.searchColumn.start;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sav.searchColumn.model.SearchColumn;
import com.sav.searchColumn.model.SearchWriteInFile;

public class StartSearchColumn {

	private final static Logger LOGGER = LoggerFactory.getLogger(StartSearchColumn.class);
	public static String ERROR_MESSAGE = "Error, required input parameters:\n" + "\n"
			+ "  -l – path on file format CSV\n" + "  -q – search string\n"
			+ "  -o – path save file with format(.txt or .csv)\n";
	public static String ERROR_MESSAGE_FORMAT = "Error format file, need csv format!\n";

	public static void main(String[] args) {

		SearchColumn start = new SearchWriteInFile();
		startCMD(start, args);
	}

	public static void startCMD(SearchColumn start, String[] args) {

		String l, q, o;
		CommandLine commandLine;
		CommandLineParser parser = new DefaultParser();

		if (args.length < 3) {
			System.out.println(ERROR_MESSAGE);
			return;
		}

		try {
			commandLine = parser.parse(startOptions(), args);
			l = commandLine.getOptionValue("l");
			if (!l.contains(".csv")) {
				System.out.println(ERROR_MESSAGE_FORMAT);
				return;
			}
			q = commandLine.getOptionValue("q");
			o = commandLine.getOptionValue("o");
			if (o.contains(".txt")) {
				start.writeTXT(l, q, o);
			} else {
				start.writeCSV(l, q, o);
			}
		} catch (ParseException e) {
			LOGGER.error("Parse error: ");
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Something exception: ");
			e.printStackTrace();
		}
	}

	public static Options startOptions() {

		Option option_l = Option.builder("l").hasArg().required(true).desc("The l option - file(csv format)").build();
		Option option_q = Option.builder("q").hasArg().required(true).desc("The q option - string search").build();
		Option option_o = Option.builder("o").hasArg().required(true).desc("The o option - file result(txt or csv)")
				.build();

		Options options = new Options();
		options.addOption(option_l);
		options.addOption(option_q);
		options.addOption(option_o);

		return options;
	}
}
