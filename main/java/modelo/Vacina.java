package modelo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vacinas")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    private String descricao;

    private int doses;

    private int periodicidade;

    private int intervalo;

    @OneToMany(mappedBy = "vacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agenda> agendas;

    public Vacina() {

    }

    public Vacina(String titulo, String descricao, int doses) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.doses = doses;
    }

    public void adicionarAgenda(Agenda agenda) {
        this.agendas.add(agenda);
        agenda.setVacina(this);
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public List<Agenda> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas) {
        this.agendas = agendas;
    }
}
