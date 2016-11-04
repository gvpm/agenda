/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gvpm
 */
@Entity
@Table(name = "telefone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByIdtelefone", query = "SELECT t FROM Telefone t WHERE t.idtelefone = :idtelefone"),
    @NamedQuery(name = "Telefone.findByNumero", query = "SELECT t FROM Telefone t WHERE t.numero = :numero"),
    @NamedQuery(name = "Telefone.findByTipo", query = "SELECT t FROM Telefone t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "Telefone.findByOperadora", query = "SELECT t FROM Telefone t WHERE t.operadora = :operadora"),
    @NamedQuery(name = "Telefone.findByDdi", query = "SELECT t FROM Telefone t WHERE t.ddi = :ddi"),
    @NamedQuery(name = "Telefone.findByDdd", query = "SELECT t FROM Telefone t WHERE t.ddd = :ddd")})
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtelefone")
    private Integer idtelefone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 45)
    @Column(name = "operadora")
    private String operadora;
    @Column(name = "ddi")
    private Integer ddi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ddd")
    private int ddd;
    @JoinColumn(name = "idcontato", referencedColumnName = "idcontato")
    @ManyToOne(optional = false)
    private Contato idcontato;

    public Telefone() {
    }

    public Telefone(Integer idtelefone) {
        this.idtelefone = idtelefone;
    }

    public Telefone(Integer idtelefone, int numero, int tipo, int ddd) {
        this.idtelefone = idtelefone;
        this.numero = numero;
        this.tipo = tipo;
        this.ddd = ddd;
    }

    public Integer getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(Integer idtelefone) {
        this.idtelefone = idtelefone;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public Integer getDdi() {
        return ddi;
    }

    public void setDdi(Integer ddi) {
        this.ddi = ddi;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public Contato getIdcontato() {
        return idcontato;
    }

    public void setIdcontato(Contato idcontato) {
        this.idcontato = idcontato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtelefone != null ? idtelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.idtelefone == null && other.idtelefone != null) || (this.idtelefone != null && !this.idtelefone.equals(other.idtelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Telefone[ idtelefone=" + idtelefone + " ]";
    }

}
