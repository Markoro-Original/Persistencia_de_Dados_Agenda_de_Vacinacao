package visao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import modelo.*;
import util.*;

import javax.persistence.EntityManager;

public class Principal {

    public static void main(String[] args) throws ParseException {

        EntityManager em = JPAUtil.getEntityManager();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dataNascimento = dateFormat.parse("1997-12-12");

        Usuario usuario = new Usuario(
                "Nome do Usuário",
                dataNascimento,
                'M',
                "Rua Exemplo",
                123,
                "Setor Exemplo",
                "Cidade Exemplo",
                "UF"
        );
        Alergia alergia1 = new Alergia("Alergia 1");
        Alergia alergia2 = new Alergia("Alergia 2");

        usuario.adicionarAlergia(alergia1);
        usuario.adicionarAlergia(alergia2);

        Vacina vacina = new Vacina(
                "Título da Vacina",
                "Descrição da Vacina",
                3,
                30,
                7
        );

        Agenda agenda = new Agenda(
                new Date(),
                "12:00",
                SituacaoAgenda.AGENDADO,
                new Date(),
                "Observações da Agenda",
                usuario,
                vacina
        );

        UsuarioDAO userDao = new UsuarioDAO(em);
        AlergiaDAO alergiaDao = new AlergiaDAO(em);
        AgendaDAO agendaDao = new AgendaDAO(em);
        VacinaDAO vacinaDao = new VacinaDAO(em);

        em.getTransaction().begin();
        alergiaDao.incluir(alergia1);
        alergiaDao.incluir(alergia2);
        userDao.incluir(usuario);
        vacinaDao.incluir(vacina);
        agendaDao.incluir(agenda);
        em.getTransaction().commit();


        em.close();

    }
}
