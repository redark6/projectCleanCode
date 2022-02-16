package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.FaxFileParser;
import project.Main;

class MainTest {
	
	File fileWithMixedStatus;
	File fileWithOkStatus;
	File fileWithIllegibleStatus;
	File fileWithErrorStatus;
	
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
	void testThatNoFileIsCreatedIfPathIsIncorect() throws Exception {
		this.removeFiles();
		String[] args = {"","-s"};
		Main.main(args);
		this.createFiles();
		assertEquals(false, fileWithMixedStatus.exists());
		assertEquals(false, fileWithOkStatus.exists());
		assertEquals(false, fileWithIllegibleStatus.exists());
		assertEquals(false, fileWithErrorStatus.exists());
	}
	
	@Test
	void testThatNoFileIscreatedIfSecondParameterIncorrect() throws Exception {
		this.removeFiles();
		String[] args = {"results/test.txt","-o"};
		Main.main(args);
		this.createFiles();
		assertEquals(false, fileWithMixedStatus.exists());
		assertEquals(false, fileWithOkStatus.exists());
		assertEquals(false, fileWithIllegibleStatus.exists());
		assertEquals(false, fileWithErrorStatus.exists());
	}
	
	@Test
	void testThatAnUnorderedFileIscreatedIfOnlyGoodPathSpecified() throws Exception {
		this.removeFiles();
		String[] args = {"results/test.txt"};
		Main.main(args);
		this.createFiles();
		assertEquals(true, fileWithMixedStatus.exists());
		assertEquals(false, fileWithOkStatus.exists());
		assertEquals(false, fileWithIllegibleStatus.exists());
		assertEquals(false, fileWithErrorStatus.exists());
	}
	
	@Test
	void testThatThreeOrderedFileArecreatedIGoodPathSpecifiedAndSortedParamIsSpecified() throws Exception {
		this.removeFiles();
		String[] args = {"results/test.txt","-s"};
		Main.main(args);
		this.createFiles();
		assertEquals(false, fileWithMixedStatus.exists());
		assertEquals(true, fileWithOkStatus.exists());
		assertEquals(true, fileWithIllegibleStatus.exists());
		assertEquals(true, fileWithErrorStatus.exists());
	}

}
