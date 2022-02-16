package project;

public enum ChecksumCodeStatusDictionary {
	OK(""),ILLEGIBLE("ILL"),ERROR("ERR");
	
	private String statusValue;
	
	public String getStatusValue() {
		return statusValue;
	}

	private ChecksumCodeStatusDictionary(String statusValue) {
		this.statusValue = statusValue;
	}
}
