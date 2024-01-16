package ufg.inf.vaccine_booking;

import java.util.List;

public class Usuario {
    private List<Alergia> alergias;
    private String sexo;
    private String UF;

    public Usuario(List<Alergia> alergias, String sexo, String UF) {
        this.alergias = alergias;
        this.sexo = sexo;
        this.UF = UF;
    }



}
