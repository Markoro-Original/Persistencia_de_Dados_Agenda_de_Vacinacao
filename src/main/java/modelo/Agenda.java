package modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "agendas")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date data;

    private String hora;

    @Enumerated(EnumType.STRING)
    private SituacaoAgenda situacao;

    private Date dataSituacao;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vacina_id")
    private Vacina vacina;

    public Agenda() {

    }

    public Agenda(Date data, String hora, SituacaoAgenda situacao, String observacoes, Usuario usuario, Vacina vacina) {
        super();
        this.data = data;
        this.hora = hora;
        this.situacao = situacao;
        this.observacoes = observacoes;
        this.usuario = usuario;
        this.vacina = vacina;
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public SituacaoAgenda getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAgenda situacao) {
        this.situacao = situacao;
    }

    public Date getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(Date dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }
}
