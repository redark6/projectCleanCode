package project;

public enum FaxNumberDictionary {
	
	ZERO("0", " _ "
			+ "| |"
			+ "|_|"
			),
	
	ONE("1", "   "
		   + "  |"
		   + "  |"
		   ),
	
	TWO("2", " _ "
			+" _|"
			+"|_ "
			),
	
	THREE("3", " _ "
			  +" _|"
			  +" _|"
			  ),
	
	
	FOUR("4", "   "
			 +"|_|"
			 +"  |"
			 ),
	
	FIVE("5", " _ "
			 +"|_ "
			 +" _|"
			 ),
	
	SIX("6", " _ "
			+"|_ "
			+"|_|"
			),
	
	ELEVEN("7", " _ "
			   +"  |"
			   +"  |"
			   ),
	
	EIGHT("8", " _ "
			  +"|_|"
			  +"|_|"
			  ),
	
	NINE("9", " _ "
			 +"|_|"
			 +" _|"
			 );
	
	public static int FAX_NUMBER_ARRAY_HEIGHT = 3;
	public static int FAX_NUMBER_ARRAY_WIDTH = 3;
	
	private String value;
	private String valueFaxRepresentation;
	
    public String getValue(){
        return this.value;
    }
    
    public String getValueFaxRepresentation(){
        return this.valueFaxRepresentation;
    }
  
    private FaxNumberDictionary(String value, String valueFaxRepresentation){
        this.value = value;
        this.valueFaxRepresentation = valueFaxRepresentation;
    }
	
}
