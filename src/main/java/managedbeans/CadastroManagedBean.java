/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import db.UsuarioDAO;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Usuario;

/**
 *
 * @author gvpm
 */
@ManagedBean(name = "CadastroMB")
@ViewScoped
public class CadastroManagedBean {

    /**
     * Creates a new instance of CadastroManagedBean
     */
    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
    private Usuario usuario = new Usuario();
    
    public String envia() {
        
         System.out.println(usuario.getNome());
         System.out.println(usuario.getLogin());
         System.out.println(usuario.getSenha());
         boolean inseriu = usuarioDAO.inserirUsuario(usuario);
              
                  if (!inseriu) {
                    usuario = new Usuario();
                    FacesContext.getCurrentInstance().addMessage(
                               null,
                               new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro",
                                           "Erro no Cadastro"));
                    return null;
              } else {
                    return "index.xhtml";
              }
        
        
    }
    public Usuario getUsuario() {
              return usuario;
        }
   
        public void setUsuario(Usuario usuario) {
              this.usuario = usuario;
        }
    
}
