package pl.capgemini.stockexchange.dataloader;

import java.io.File;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Service
public class DataLoaderFromCsvFile {
	
	@Autowired
	CsvStockFileDataParser csvStockFileDataParser;
	
	@Value(value = "#{jpaProps['dbFilePath']}")
	String csvFilePath;
	
	public void createMysqlDBFromCsvFile() {
		File csvDbFile = new File(csvFilePath); 
		try {
			Scanner csvScanner = new Scanner(csvDbFile);

			while (csvScanner.hasNextLine()) {
				String oneLine = csvScanner.nextLine();
				csvStockFileDataParser.prepareQuery(oneLine);
			}
			csvScanner.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
