package Laboral.rules.format;

import Laboral.rules.exceptions.DatosNoCorrectosException;

public final class Check {
    private Check() {}

    private static boolean content(String cadena) throws DatosNoCorrectosException {
        boolean isWrong = false;
        if (cadena == null || cadena.isEmpty() || cadena.isBlank()) {
            isWrong = true;
        }
        return isWrong;
    }

    public static boolean dni(String dni) throws DatosNoCorrectosException {
        if (content(dni) || !dni.matches(RegexPattern.DNI)) {
            throw new DatosNoCorrectosException();
        }
        return true;
    }

    public static boolean name(String name) throws DatosNoCorrectosException {
        if (content(name) || !name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+( [A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$\n")) {
            throw new DatosNoCorrectosException();
        }
        return true;
    }
}
