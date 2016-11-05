/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import db.*;
import model.*;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author gvpm
 */
@Named(value = "AgendaMB")
@Dependent
public class AgendaManagedBean {

    private UsuarioDAO usuarioDAO = UsuarioDAO.getInstance();
    private Usuario usuario = new Usuario();
    private String nome;
    
    private ContatoDAO contatoDAO = ContatoDAO.getInstance();
    private Contato contato = new Contato();
    int idUsuario;
    

    /**
     * Creates a new instance of AgendaManagedBean
     *
     * @return
     */
    //executado no inicio e verifica o id do usuario da session
    public String getLoga() {
        System.out.println("ioioioio");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        
        if (sessionMap.get("idUsuario") != null) {
            idUsuario = (int) sessionMap.get("idUsuario");
        } else {
            idUsuario = -1;
        }

        usuario = usuarioDAO.getUsuarioFromId(idUsuario);
        if (usuario == null) {
            nome = null;
            return "Você não está logado";
        } else {
            //Preenche com o id do usuario encontrado a sessao
//            FacesContext.getCurrentInstance().addMessage(
//                    null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, usuario.getNome(),
//                            usuario.getNome()));
            nome = usuario.getNome();
            System.out.println("Logado" + nome);

            return "Bem vindo " + nome + "!";
        }

    }
    
        public String cadastra() {
            
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        
        if (sessionMap.get("idUsuario") != null) {
            idUsuario = (int) sessionMap.get("idUsuario");
        } else {
            idUsuario = -1;
        }    

        contato.setIdusuario(usuarioDAO.getUsuarioFromId(idUsuario));
            System.out.println("nome"+contato.getNome());
            System.out.println("email"+contato.getEmail());
        boolean inseriu = contatoDAO.inserirContato(contato);

        if (!inseriu) {
            contato = new Contato();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no cadastro",
                            "Erro no Cadastro"));
            return null;
        } else {
            return "agendaFace.xhtml";
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

   
    
    
    

}
