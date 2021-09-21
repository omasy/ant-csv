# ant-csv
Antware CSV Repo Utility Class is all in one CSV Object built on top of Common CSV Java Library for creating a csv file, writing and manipulating excel document.

SPECIFICATION:
- Base Class is CSVUtils which extend child classes
- CSVWriter is the write class which is called within the base class
- CSVReader is the reader class which is called within the base class

USAGE:
import system.csv.*;

Include the jar file ant-csv.jar in your list of classpath or in your reference library if your using eclipse as your IDE

SAMPLE CODE:
CSVUtils csvUtil=new CSVUtils();
fields=new String[]{"Coming", "Going", "Base", "Count"};
header=new Object[]{"Coming", "Going", "Base", "Count"};

/*** Calling the reader ***/
CSVReader reader=csvUtil.reader();

/*** Calling the writer ***/
CSVWriter writer=csvUtil.writer();

/*** Reading a created CSV File ***/
reader.header(fields);
reader.fIn(logPath);
logData=reader.readCSV(fields);

/*** Writing a CSV File ***/
writer.header(header);
writer.fOut(logPath);
writer.writeCSV(newData);

DEPENDENCIES:
import org.apache.commons.csv.*;

