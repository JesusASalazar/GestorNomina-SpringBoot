package Laboral.rules.exceptions;

public class DatosNoCorrectosException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public DatosNoCorrectosException() {
        super("Datos no correctos");
    }

    public DatosNoCorrectosException(String msg) {
        super(msg);
    }
}
