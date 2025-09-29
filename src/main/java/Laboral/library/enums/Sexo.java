package Laboral.library.enums;

import Laboral.rules.exceptions.DatosNoCorrectosException;

public enum Sexo {
    H('H'), M('M');

    public final char sex;

    Sexo(char sex) {
        this.sex = sex;
    }

    public static Sexo fromChar(char c) throws DatosNoCorrectosException {
        for (Sexo g : Sexo.values()) {
            if (g.sex == c) {
                return g;
            }
        }
        throw new DatosNoCorrectosException();
    }

}
