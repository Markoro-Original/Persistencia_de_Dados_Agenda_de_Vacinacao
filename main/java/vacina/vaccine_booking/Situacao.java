package ufg.inf.vaccine_booking;

public enum Situacao {
    AGENDADO("Agendado"),
    CANCELADO("Cancelado"),
    REALIZADO("Realizado");

    private String situacao;

    Situacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }
}
