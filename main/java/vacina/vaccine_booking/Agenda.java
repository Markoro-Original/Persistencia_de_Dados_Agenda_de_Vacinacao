package ufg.inf.vaccine_booking;

import java.util.Date;

public class Agenda {

    private Date data;
    private Situacao situacao;
    private Date dataSituacao;

    public Agenda(Date data, Date dataSituacao) {
        this.data = data;
        this.dataSituacao = dataSituacao;
    }

    public Date getData() {
        return this.data;
    }

    public Situacao getSituacao() {
        return this.situacao;
    }

    public Date getDataSituacao() {
        return this.dataSituacao;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDataSituacao(Date dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public String toString() {
        return "Agenda [data=" + this.data + ", situacao=" + this.situacao + ", dataSituacao=" + this.dataSituacao + "]";
    }

}
