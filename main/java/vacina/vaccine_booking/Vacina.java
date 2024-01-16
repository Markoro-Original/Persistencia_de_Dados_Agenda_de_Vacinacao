package ufg.inf.vaccine_booking;

public class Vacina {
    private int doses;
    private int periodicidade;
    private int intervalo;

    public Vacina(int doses, int periodicidade, int intervalo) {
        this.doses = doses;
        this.periodicidade = periodicidade;
        this.intervalo = intervalo;
    }

    public int getDoses() {
        return this.doses;
    }

    public int getPeriodicidade() {
        return this.periodicidade;
    }

    public int getIntervalo() {
        return this.intervalo;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public String toString() {
        return "Vacina [doses=" + this.doses + ", periodicidade=" + this.periodicidade + ", intervalo=" + this.intervalo + "]";
    }

}
