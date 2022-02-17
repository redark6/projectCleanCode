package project.exceptions;


public class ToManyArgumentsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ToManyArgumentsException(String message) {
        super(message);
    }

    public static ToManyArgumentsException defaultMsg() {
        return new ToManyArgumentsException("trop d'argumet: le premier doit être le chemin du fichier et le second ( optionnel ) doit être '-s' pour trirer les codes de sorties par statut");
    }
}
