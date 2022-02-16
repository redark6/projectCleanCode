package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import project.Code;
import project.FaxFileParser;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FaxFileParserTests {

	private FaxFileParser faxFileParser;
	private Code codeOk;
	private Code codeIllegible;
	private Code codeError;
	private List<Code> listOfCodeWithOneCodeOfEachStatus;
	
	File fileWithMixedStatus;
	File fileWithOkStatus;
	File fileWithIllegibleStatus;
	File fileWithErrorStatus;
	
	@BeforeAll
	void init() {
		this.faxFileParser = new FaxFileParser();
		this.codeOk = new Code("123456789");
		this.codeIllegible = new Code("12345678?");
		this.codeError = new Code("123456788");
		this.codeOk.evaluateCodeStatus();
		this.codeIllegible.evaluateCodeStatus();
		this.codeError.evaluateCodeStatus();
		
		this.listOfCodeWithOneCodeOfEachStatus = new ArrayList<Code>();
		listOfCodeWithOneCodeOfEachStatus.add(codeOk);
		listOfCodeWithOneCodeOfEachStatus.add(codeIllegible);
		listOfCodeWithOneCodeOfEachStatus.add(codeError);
	}
	
	@AfterEach
	void removeFiles() {
		if(this.fileWithMixedStatus != null) {
			this.fileWithMixedStatus.delete();
		}
		if(this.fileWithOkStatus != null) {
			this.fileWithOkStatus.delete();
		}
		if(this.fileWithIllegibleStatus != null) {
			this.fileWithIllegibleStatus.delete();
		}
		if(this.fileWithErrorStatus != null) {
			this.fileWithErrorStatus.delete();
		}
	}

	void createFiles() {
		this.fileWithMixedStatus = new File(FaxFileParser.RESULT_FILE_LOCATION_MIXED_STATUS_CODE);
		this.fileWithOkStatus = new File(FaxFileParser.RESULT_FILE_LOCATION_STATUS_OK_CODE);
		this.fileWithIllegibleStatus = new File(FaxFileParser.RESULT_FILE_LOCATION_STATUS_ILLEGIBLE_CODE);
		this.fileWithErrorStatus = new File(FaxFileParser.RESULT_FILE_LOCATION_STATUS_ERROR_CODE);
	}
	
	@Test
	void testParsingOfCodes() {
	}
	
	@Test
	void testParsingOfCodesInNoSortingModeWriteOneFile() throws IOException {
		this.removeFiles();
		faxFileParser.computeFaxCodeTraslation("results/test.txt", false);
		this.createFiles();
		assertEquals(true, fileWithMixedStatus.exists());
		assertEquals(false, fileWithOkStatus.exists());
		assertEquals(false, fileWithIllegibleStatus.exists());
		assertEquals(false, fileWithErrorStatus.exists());
	}
	

	@Test
	void testParsingOfCodesInSortingModeWriteThreeFile() throws IOException {
		this.removeFiles();
		faxFileParser.computeFaxCodeTraslation("results/test.txt", true);
		this.createFiles();
		assertEquals(false, fileWithMixedStatus.exists());
		assertEquals(true, fileWithOkStatus.exists());
		assertEquals(true, fileWithIllegibleStatus.exists());
		assertEquals(true, fileWithErrorStatus.exists());
	}
	
	@Test
	void testParsingOfCodesWithUnknwonRepresentationGiveInterogation() {
		char[][] faxCode = {
				{' ',' ',' ',' ','_',' ',' ','_',' ',' ',' ',' ',' ','_',' ',' ','_',' ',' ','_',' ',' ','_',' ',' ','_',' '},
				{' ',' ','|',' ','_','|',' ','_','|','|','_','|','|','_',' ','|','_',' ',' ',' ','|','|','_','|','|','_','|'},
				{' ',' ','|','|','_',' ',' ','_','|',' ',' ','|',' ','_','|','|','_','|',' ',' ','|','|','_','|','|',' ','|'},
		};
		Code translatedCode = faxFileParser.parseFaxFileFaxPresentationToCode(faxCode);
		assertEquals(true, translatedCode.getCode().equals("12345678?"));
	}
	
	@Test
	void testParsingOfCodeWriteFile() throws IOException {
		this.removeFiles();
		List<String> expected = new ArrayList<String>();
		expected.add(this.codeOk.toString());
		expected.add(this.codeError.toString());
		expected.add(this.codeIllegible.toString());
		
		List<String> result = new ArrayList<String>();

		faxFileParser.computeFaxCodeTraslation("results/test.txt", false);
		this.fileWithMixedStatus = new File(FaxFileParser.RESULT_FILE_LOCATION_MIXED_STATUS_CODE);
		FileReader fileReader = new FileReader(fileWithMixedStatus);
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        
        while((line = br.readLine()) != null) {
        	result.add(line);
        }
        br.close();
        assertEquals(expected, result);
		
		this.fileWithMixedStatus.delete();
	}

}
