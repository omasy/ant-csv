package system.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVUtils {
	// SETTING THE CSV METHODS
	public CSVReader reader(){
		return new CSVReader();
	}
	
	public CSVWriter writer(){
		return new CSVWriter();
	}
	
	// END OF CLASS
}



/************* HERE WE CONSTRUCT THE CSV WRITER ***************/
class CSVWriter{
	// SETTING PUBLIC VARIABLES
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static Object[] FILE_HEADER;
	private String FilePath;
	// HERE WE CONSTRUCT CLASS
	public CSVWriter(){};
	
	/*********** HERE WE SET HELPER METHODS ************/
	public void header(Object[] head){
		FILE_HEADER=head;
	}
	
	public void fOut(String file){
		FilePath=file;
	}
	
	public int  writeCSV(String[][] datas) {
		// HERE WE START CONSTRUCTING THE CSV WRITER
		int trueValue=0;
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		// HERE WE START PROCESSING
		if(datas!=null && datas.length>0){
			//Create the CSVFormat object with "\n" as a record delimiter 
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
					
			try {		
				//initialize FileWriter object
				fileWriter = new FileWriter(FilePath);
				
				//initialize CSVPrinter object   
				csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);    
		        	
				//Create CSV file header    
				csvFilePrinter.printRecord(FILE_HEADER);
				
				//Write a new student object list to the CSV file	
				for (String[] data : datas) {
					List<String> dataRecord = new ArrayList<String>();
					// Here we set content to write
					for(int i=0; i<data.length; i++){
						dataRecord.add(data[i]);
					}
					
					// Here we print data to file
					csvFilePrinter.printRecord(dataRecord);
				}
				// Here we set the returner
				trueValue=1;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {	
				try {	
					fileWriter.flush();
					fileWriter.close();
					csvFilePrinter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Here we return
		return trueValue;
	}
	
	// END OF CLASS
}


/************ HERE WE CONSTRUCT THE CSV READER ****************/
class CSVReader{
	// CREATING PUBLIC VARIABLE
    private static String[] FILE_HEADER_MAPPING;
    private String FilePath;
    
    // HERE WE CONSTRUCT CLASS
    public CSVReader(){};
    
    /*********** CREATING HELPER METHODS ***************/
    public void header(String[] heads){
    	FILE_HEADER_MAPPING=heads;
    }
    
    public void fIn(String file){
    	FilePath=file;
    }
	
    
    /********** HERE WE CREATE READ CSV METHOD *********/
	public String[][] readCSV(String[] fields) {
		// HERE WE START PROCESSING THE READ CSV CONTENTS
		String[][] contents = {};
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		
		// HERE WE START PROCESSING
		if(fields!=null){
			//Create the CSVFormat object with the header mapping
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
     
			try {
        	
				//Create a new list of student to be filled by CSV file data
				//List<String> content=new ArrayList<String>();
            
				//initialize FileReader object
				fileReader = new FileReader(FilePath);
            
				//initialize CSVParser object
				csvFileParser = new CSVParser(fileReader, csvFileFormat);
            
				//Get a list of CSV file records
				List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            
				//Read the CSV file records starting from the second record to skip the header
				contents=new String[csvRecords.size()-1][fields.length];
				for (int i = 1; i < csvRecords.size(); i++) {
					CSVRecord record = csvRecords.get(i);
					//Create a new student object and fill his data
					for(int j=0; j<fields.length; j++){
						contents[i-1][j]=record.get(fields[j]);
					}
					// Here we submit to contents
					// contents.addAll(content);
				} // end of loop
			} 
			catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fileReader.close();
					csvFileParser.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			// Here we return
			return contents;
		}
	
	// END OF CLASS
}