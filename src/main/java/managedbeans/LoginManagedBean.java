package managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import db.UsuarioDAO;
import java.util.Map;
import javax.faces.context.ExternalContext;
import model.Usuario;

@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginManagedBean {

    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
    private Usuario usuario = new Usuario();

    public String envia() {

//        System.out.println(usuario.getLogin());
//        System.out.println(usuario.getSenha());
        usuario = usuarioDAO.getUsuario(usuario.getLogin(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                            "Erro no Login!"));
            return null;
        } else {
            //Preenche com o id do usuario encontrado a sessao
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("idUsuario", usuario.getIdusuario());

            return "agendaFace.xhtml";
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
