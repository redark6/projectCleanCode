package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import project.ChecksumCodeStatusDictionary;
import project.Code;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CodeTests {
	
	private Code codeOk;
	private Code codeIllegible;
	private Code codeError;
	private List<Code> listOfCodeWithOneCodeOfEachStatus;
	
	@BeforeAll
	void init() {
		this.codeOk = new Code("123456789");
		this.codeIllegible = new Code("12345?789");
		this.codeError = new Code("123457789");
		this.codeOk.evaluateCodeStatus();
		this.codeIllegible.evaluateCodeStatus();
		this.codeError.evaluateCodeStatus();
		
		this.listOfCodeWithOneCodeOfEachStatus = new ArrayList<Code>();
		listOfCodeWithOneCodeOfEachStatus.add(codeOk);
		listOfCodeWithOneCodeOfEachStatus.add(codeIllegible);
		listOfCodeWithOneCodeOfEachStatus.add(codeError);
	}

	@Test
	void TestIfCodeValueIsSameAsGiven() {
		String codeValue = "123456789";
		Code code = new Code(codeValue);
		assertEquals(codeValue, code.getCode());
	}

	@Test
	void TestIfCodeStatusIsOk() {
		assertEquals(ChecksumCodeStatusDictionary.OK, codeOk.getCodeStatus());
	}
	
	@Test
	void TestIfCodeStatusIsIllegible() {
		assertEquals(ChecksumCodeStatusDictionary.ILLEGIBLE, codeIllegible.getCodeStatus());
	}
	
	@Test
	void TestIfCodeStatusIsError() {
		assertEquals(ChecksumCodeStatusDictionary.ERROR, codeError.getCodeStatus());
	}
	
	@Test
	void TestIfCodeChecksumForOkEqualfZero() {
		assertEquals(0, codeOk.computeChecksum());
	}

	@Test
	void TestIfCodeChecksumForIllegibleIsLowerThanZero() {
		assertEquals(true, codeIllegible.computeChecksum() < 0);
	}
	
	@Test
	void TestIfCodeChecksumForErrorIsGreaterThanZero() {
		assertEquals(true, codeError.computeChecksum() > 0);
	}
	
	@Test
	void TestStringCodeRepresentationForCodeOk() {
		String expected = codeOk.getCode();
		assertEquals(expected, codeOk.toString());
	}

	@Test
	void TestStringCodeRepresentationForCodeIllegible() {
		String expected = codeIllegible.getCode() + " " + ChecksumCodeStatusDictionary.ILLEGIBLE.getStatusValue();
		assertEquals(expected, codeIllegible.toString());
	}
	
	@Test
	void TestStringCodeRepresentationForCodeError() {
		String expected = codeError.getCode() + " " + ChecksumCodeStatusDictionary.ERROR.getStatusValue();
		assertEquals(expected, codeError.toString());
	}
	
	@Test
	void TestGetCodesFromListWithStatusOkReturnOnlyCodesOk() {
		List<Code> expected = new ArrayList<Code>();
		expected.add(codeOk);
		assertEquals(expected, Code.getCodesWithStatusOk(listOfCodeWithOneCodeOfEachStatus));
	}

	@Test
	void TestGetCodesFromListWithStatusIllegibleReturnOnlyCodesIllegible() {
		List<Code> expected = new ArrayList<Code>();
		expected.add(codeIllegible);
		assertEquals(expected, Code.getCodesWithStatusIllegible(listOfCodeWithOneCodeOfEachStatus));
	}
	
	@Test
	void TestGetCodesFromListWithStatusErrorReturnOnlyCodesError() {
		List<Code> expected = new ArrayList<Code>();
		expected.add(codeError);
		assertEquals(expected, Code.getCodesWithStatusError(listOfCodeWithOneCodeOfEachStatus));
	}
}
