package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.Contato;

public class ContatoDAO {

    private static ContatoDAO instance;

    private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("contatos");
    private EntityManager em = factory.createEntityManager();

    private ContatoDAO() {

    }

    public static ContatoDAO getInstance() {

        if (instance == null) {
            instance = new ContatoDAO();
        }
        return instance;

    }

    public Contato getContato(String login, String senha) {

        try {
            Contato usuario = (Contato) em
                    .createQuery(
                            "SELECT u from Usuario u where u.login = :login and u.senha = :senha")
                    .setParameter("login", login)
                    .setParameter("senha", senha).getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Contato getContatoFromId(int id) {

        try {
            Contato contato = (Contato) em
                    .createQuery(
                            "SELECT c from Contato c where c.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();

            return contato;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean inserirContato(Contato contato) {
        try {
            em.getTransaction().begin();
            em.persist(contato);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarContato(Contato contato) {
        try {
            em.remove(contato);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
