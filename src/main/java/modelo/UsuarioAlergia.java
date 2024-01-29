package modelo;

import javax.persistence.*;

@Entity
@Table(name = "usuario_alergia")
public class UsuarioAlergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "alergia_id")
    private Alergia alergia;

    public UsuarioAlergia () {

    }

    public UsuarioAlergia(Usuario usuario, Alergia alergia) {
        this.usuario = usuario;
        this.alergia = alergia;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Alergia getAlergia() {
        return alergia;
    }

    public void setAlergia(Alergia alergia) {
        this.alergia = alergia;
    }
}

