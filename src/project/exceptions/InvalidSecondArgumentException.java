package project.exceptions;


public class InvalidSecondArgumentException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private InvalidSecondArgumentException(String message) {
        super(message);
    }

    public static InvalidSecondArgumentException defaultMsg() {
        return new InvalidSecondArgumentException("seulement '-s' est permis en tant que second argument pour trier les codes de sortie par statut");
    }
}
