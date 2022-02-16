package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FaxFileParser {
	
	public static String FILE_BASE_LOCATION = "results/";
	
	public static String RESULT_FILE_LOCATION_MIXED_STATUS_CODE = FILE_BASE_LOCATION + "Results.txt";
	public static String RESULT_FILE_LOCATION_STATUS_OK_CODE = FILE_BASE_LOCATION + "Authorized.txt";
	public static String RESULT_FILE_LOCATION_STATUS_ILLEGIBLE_CODE = FILE_BASE_LOCATION + "Unknown.txt";
	public static String RESULT_FILE_LOCATION_STATUS_ERROR_CODE = FILE_BASE_LOCATION + "Errored.txt";
	
	private FileTranscriptor fileTranscriptor;
	
	public FaxFileParser() {
		this.fileTranscriptor = new FileTranscriptor();
	}
	
	public List<char[][]> getFaxFileContent(String fileLocation) throws IOException{
		return fileTranscriptor.getFax(fileLocation);
	}
	
	public Code parseFaxFileFaxPresentationToCode(char[][] faxCode) {
		String translatedCode = "";
		String numberFromFaxRepresentation = "";
		for (int numberAtPosition = 0; numberAtPosition < Code.NUMBERS_IN_CODE; numberAtPosition++) {
			for (int charHeightPositionInArray = 0; charHeightPositionInArray < FaxNumberDictionary.FAX_NUMBER_ARRAY_HEIGHT; charHeightPositionInArray++) {
				numberFromFaxRepresentation += String.valueOf(Arrays.copyOfRange(faxCode[charHeightPositionInArray], numberAtPosition * FaxNumberDictionary.FAX_NUMBER_ARRAY_WIDTH, ((numberAtPosition + 1 ) * FaxNumberDictionary.FAX_NUMBER_ARRAY_WIDTH)));
			}
			translatedCode += getStringNumberRepresentationFromFaxRepresentation(numberFromFaxRepresentation);
			numberFromFaxRepresentation = "";
		}
		return new Code(translatedCode);
	}
	
	private String getStringNumberRepresentationFromFaxRepresentation(String faxRepresentation) {
		for (FaxNumberDictionary dictionaryFaxElement : FaxNumberDictionary.values()) { 
			if(faxRepresentation.equals(dictionaryFaxElement.getValueFaxRepresentation())) {
				return dictionaryFaxElement.getValue();
			}
		}
		return "?";
	}
	
	public void computeFaxCodeTraslation(String fileLocation, boolean sortedByStatus) throws IOException {
		List<char[][]> entries = this.getFaxFileContent(fileLocation);
		List<Code> codes = new ArrayList<Code>();
		Code code;
		
	    for (char[][] entry : entries) {
			code = parseFaxFileFaxPresentationToCode(entry);
			code.evaluateCodeStatus();
			codes.add(code);
	    }
	    
	    if(sortedByStatus == true) {
	    	List<String> stringifiedCodesRepresentationWithStatusOk = this.parsCodeListToCodeStringRepresentationList(Code.getCodesWithStatusOk(codes));
	    	List<String> stringifiedCodesRepresentationWithStatusIllegible = this.parsCodeListToCodeStringRepresentationList(Code.getCodesWithStatusIllegible(codes));
	    	List<String> stringifiedCodesRepresentationWithStatusError = this.parsCodeListToCodeStringRepresentationList(Code.getCodesWithStatusError(codes));
		    this.fileTranscriptor.writeFaxDecodingResult(RESULT_FILE_LOCATION_STATUS_OK_CODE, stringifiedCodesRepresentationWithStatusOk);
		    this.fileTranscriptor.writeFaxDecodingResult(RESULT_FILE_LOCATION_STATUS_ILLEGIBLE_CODE, stringifiedCodesRepresentationWithStatusIllegible);
		    this.fileTranscriptor.writeFaxDecodingResult(RESULT_FILE_LOCATION_STATUS_ERROR_CODE, stringifiedCodesRepresentationWithStatusError);
	    }else {
	    	List<String> stringifiedCodes = this.parsCodeListToCodeStringRepresentationList(codes);
		    this.fileTranscriptor.writeFaxDecodingResult(RESULT_FILE_LOCATION_MIXED_STATUS_CODE, stringifiedCodes);
	    }
	    
	}
	
	private List<String> parsCodeListToCodeStringRepresentationList(List<Code> codes){
		List<String> CodeStringRepresentationList = new ArrayList<String>();
		codes.forEach(code -> {
			CodeStringRepresentationList.add(code.toString());
		});
		return CodeStringRepresentationList;
	}
	
}
