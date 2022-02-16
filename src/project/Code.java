package project;

import java.util.ArrayList;
import java.util.List;

public class Code {
	
	public static int NUMBERS_IN_CODE = 9;
	
	private String code;
	private ChecksumCodeStatusDictionary codeStatus;
	
	public Code(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ChecksumCodeStatusDictionary getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(ChecksumCodeStatusDictionary status) {
		this.codeStatus = status;
	}
	
	public double computeChecksum() {
		if(!this.code.contains("?")) {
			int sum = 0;
			for (int numberPosition = 0; numberPosition < Code.NUMBERS_IN_CODE; numberPosition++) {
				sum += ( Code.NUMBERS_IN_CODE - numberPosition ) * Character.getNumericValue(this.code.charAt(numberPosition));
			}
			return sum % 11;
		}else {
			return -1;
		}
	}
	
	public void evaluateCodeStatus() {
		double checkSumResult = this.computeChecksum();
		if(checkSumResult < 0) {
			this.setCodeStatus(ChecksumCodeStatusDictionary.ILLEGIBLE);
		}else if(checkSumResult > 0) {
			this.setCodeStatus(ChecksumCodeStatusDictionary.ERROR);
		}else {
			this.setCodeStatus(ChecksumCodeStatusDictionary.OK);
		}	
	}
	
	private static List<Code> getCodesWithSpecifiedStatus(List<Code> codes, ChecksumCodeStatusDictionary specifiedStatus){
		List<Code> codesWithSpecifiedStatus = new ArrayList<Code>();
		codes.forEach(code ->{
			if(code.getCodeStatus() == specifiedStatus) {
				codesWithSpecifiedStatus.add(code);
			}
		});
		return codesWithSpecifiedStatus;
	}
	
	public static List<Code> getCodesWithStatusOk(List<Code> codes){
		return getCodesWithSpecifiedStatus(codes,ChecksumCodeStatusDictionary.OK);
	}
	
	public static List<Code> getCodesWithStatusIllegible(List<Code> codes){
		return getCodesWithSpecifiedStatus(codes,ChecksumCodeStatusDictionary.ILLEGIBLE);
	}
	
	public static List<Code> getCodesWithStatusError(List<Code> codes){
		return getCodesWithSpecifiedStatus(codes,ChecksumCodeStatusDictionary.ERROR);
	}

	@Override
	public String toString() {
		return this.codeStatus == ChecksumCodeStatusDictionary.OK ? this.code : this.code + " " + this.codeStatus.getStatusValue();
	}
	
	
}
