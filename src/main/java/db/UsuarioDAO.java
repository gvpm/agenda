package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.Usuario;

public class UsuarioDAO {

    private static UsuarioDAO instance;

    private EntityManagerFactory factory = Persistence
            .createEntityManagerFactory("usuarios");
    private EntityManager em = factory.createEntityManager();

    private UsuarioDAO() {

    }

    public static UsuarioDAO getInstance() {

        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;

    }

    public Usuario getUsuario(String login, String senha) {

        try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                            "SELECT u from Usuario u where u.login = :login and u.senha = :senha")
                    .setParameter("login", login)
                    .setParameter("senha", senha).getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public Usuario getUsuarioFromId(int id) {

        try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                            "SELECT u from Usuario u where u.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();

            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean inserirUsuario(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarUsuario(Usuario usuario) {
        try {
            em.remove(usuario);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
