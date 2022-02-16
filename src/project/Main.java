package project;

public class Main {

	public static void main(String[] args) throws Exception {
		FaxFileParser faxFileParser = new FaxFileParser();
		boolean sortedByStatus = false;
		
		try {
			
		switch (args.length) {
		case 0:
			throw new Exception("pas assez d'arguments: le premier doit être le chemin du fichier et le second ( optionnel ) doit être '-s' pour trirer les codes de sorties par statut");
		case 1:
			sortedByStatus = false;
			break;
		case 2:
			if(args[1].equals("-s")) {
				sortedByStatus = true;
			}else {
				throw new Exception("seulement '-s' en tant que second argument pour trier les codes de sortie par statut");
			}
			break;
		default:
			throw new Exception("trop d'argumet: le premier doit être le chemin du fichier et le second ( optionnel ) doit être '-s' pour trirer les codes de sorties par statut");
		}
		
		faxFileParser.computeFaxCodeTraslation(args[0],sortedByStatus);
		System.out.println("opération effectué avec succès !");
		
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}
