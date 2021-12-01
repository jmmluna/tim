package es.jmmluna.tim.domain.model.customer;

public class DNIValidator {

	public boolean validate(String dni) {

		// Solo puede medir 9 (sin guión) o 10 (con guión)
		if (dni.length() != 9 && dni.length() != 10) {
			return false;
		}
		// Si mide 10, debe tener un guión en la posición 8
		if (dni.length() == 10) {
			// Extraerlo y regresar false si no es un guión
			String posibleGuion = String.valueOf(dni.charAt(8));
			if (!posibleGuion.equals("-")) {
				return false;
			}
		}
		// Hasta ahora sabemos que mide 9 o 10 y que el guión es válido (esté presente o
		// no)
		String primerosOcho = dni.substring(0, 8);
		String ultimo = String.valueOf(dni.charAt(dni.length() - 1));
		// Comprobar que los primeros 8 sean numéricos
		if (!primerosOcho.matches("[0-9]+")) {
			return false;
		}
		// Comprobar que el último sea una letra
		if (!ultimo.matches("[A-Z]+")) {
			return false;
		}
		return true;
	}

	public char getDNILetter(int dni) {
		String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
		return letters.charAt(dni % 23);
	}

}
