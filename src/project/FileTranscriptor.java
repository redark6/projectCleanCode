package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class FileTranscriptor {
	
	public List<char[][]> getFax(String fileLocation) throws IOException {
		List<char[][]> faxCodes = new ArrayList<char[][]>();
		
		String line;
		File file = new File(fileLocation);
        Reader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        
        char[][] currentFaxCode = new char[FaxNumberDictionary.CODE_REPRESENTATION_LINE_FILE_HEIGHT][FaxNumberDictionary.CODE_REPRESENTATION_LINE_FILE_LENGTH];
        int currentFaxCodeLine = 0;
        
        while((line = br.readLine()) != null) {
        	if(currentFaxCodeLine < FaxNumberDictionary.CODE_REPRESENTATION_LINE_FILE_HEIGHT) {
        		line.getChars(0, FaxNumberDictionary.CODE_REPRESENTATION_LINE_FILE_LENGTH, currentFaxCode[currentFaxCodeLine], 0);
        		currentFaxCodeLine++;
        	}else {
        		faxCodes.add(currentFaxCode);
        		currentFaxCode = new char[FaxNumberDictionary.CODE_REPRESENTATION_LINE_FILE_HEIGHT][FaxNumberDictionary.CODE_REPRESENTATION_LINE_FILE_LENGTH];
        		currentFaxCodeLine = 0;
        	}
        }
        faxCodes.add(currentFaxCode);
        br.close();
        return faxCodes;
	}
	
	public void writeFaxDecodingResult(String fileLocation, List<String> faxDecodingResults) throws IOException{
		File outputFile = new File(fileLocation);
		outputFile.getParentFile().mkdirs();
		outputFile.createNewFile();
		FileWriter fileWriter = new FileWriter(outputFile);
		
	    for (String result : faxDecodingResults) {
	    	fileWriter.write(result);
	    	fileWriter.write(System.getProperty( "line.separator" ));
	    }
	 
		fileWriter.close();
	}
	

}
