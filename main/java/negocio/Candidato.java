package negocio;


import java.util.Date;

public class Candidato {
    private int codigo;
    private String nome;
    private char sexo;
    private Date dataNasc;
    private String cargoPretendido;
    private String textoCurriculo;

    public Candidato(int codigo, String nome, char sexo, Date dataNasc, String cargoPretendido, String textoCurriculo) {
        this.codigo = codigo;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.cargoPretendido = cargoPretendido;
        this.textoCurriculo = textoCurriculo;
    }



    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getCargoPretendido() {
        return cargoPretendido;
    }

    public String getTextoCurriculo() {
        return textoCurriculo;
    }
}
