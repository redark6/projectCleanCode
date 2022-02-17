package project.exceptions;


public class NoEnoughArgumentsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private NoEnoughArgumentsException(String message) {
        super(message);
    }

    public static NoEnoughArgumentsException defaultMsg() {
        return new NoEnoughArgumentsException("pas assez d'arguments: le premier doit être le chemin du fichier et le second ( optionnel ) doit être '-s' pour trirer les codes de sorties par statut");
    }
}
