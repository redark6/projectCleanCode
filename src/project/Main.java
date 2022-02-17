package project;

import project.exceptions.InvalidSecondArgumentException;
import project.exceptions.NoEnoughArgumentsException;
import project.exceptions.ToManyArgumentsException;

public class Main {

	public static void main(String[] args) throws Exception {
		FaxFileParser faxFileParser = new FaxFileParser();
		boolean sortedByStatus = false;
		
		try {
			
		switch (args.length) {
		case 0:
			throw NoEnoughArgumentsException.defaultMsg();
		case 1:
			sortedByStatus = false;
			break;
		case 2:
			if(args[1].equals("-s")) {
				sortedByStatus = true;
			}else {
				throw InvalidSecondArgumentException.defaultMsg();
			}
			break;
		default:
			throw ToManyArgumentsException.defaultMsg();
		}
				
		faxFileParser.computeFaxCodeTraslation(args[0],sortedByStatus);
		System.out.println("opération effectué avec succès !");
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}
