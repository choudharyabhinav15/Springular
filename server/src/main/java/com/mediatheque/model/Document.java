package com.mediatheque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediatheque.service.Empruntable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author Ghiles FEGHOUL
 * @Date 26/01/2018
 * @Licence MIT
 */
@Entity(name = "documents")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document implements Empruntable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String code;

    protected String title;

    protected String author;

    protected String year;

    protected String gender;

    protected boolean empruntable;

    protected boolean emprunte;

    protected int nbEmprunt;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Localisation localization;

    protected int nbEmpruntTotal = 0;


    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getGender() {
        return gender;
    }

    public boolean isEmpruntable() {
        return empruntable;
    }

    public boolean isEmprunte() {
        return emprunte;
    }

    public int getNbEmprunt() {
        return nbEmprunt;
    }

    public Localisation getLocalization() {
        return localization;
    }

    public int getNbEmpruntTotal() {
        return nbEmpruntTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmpruntable(boolean empruntable) {
        this.empruntable = empruntable;
    }

    public void setEmprunte(boolean emprunte) {
        this.emprunte = emprunte;
    }

    public void setNbEmprunt(int nbEmprunt) {
        this.nbEmprunt = nbEmprunt;
    }

    public void setLocalization(Localisation localization) {
        this.localization = localization;
    }

    public void setNbEmpruntTotal(int nbEmpruntTotal) {
        this.nbEmpruntTotal = nbEmpruntTotal;
    }

    /**
     * Autorise l'emprunt du document.
     */
    public void makeEmpruntable() throws Exception {
        //CORRECTION
        if (empruntable) {
            throw new Exception("Document metEmpruntable empruntable" + this);
        }
        empruntable = true;
    }

    /**
     * Interdit l'emprunt du document.
     */
    public void makeConsultable() throws Exception {
        if (!empruntable) {
            throw new Exception("Document metConsultable consultable" + this);
        }
        if (emprunte) {
            throw new Exception("Document metConsultable emprunte" + this);
        }
        empruntable = false;
    }

    /**
     * Retourne vrai si le document est empruntable.
     */

    @JsonIgnore
    public boolean estEmpruntable() { return empruntable; }

    // Operations du DME
    /**
     * <TT>emprunter</TT> est appelee lors de l'emprunt d'un document.
     * Les statistiques sont mises a jour.
     */

    @JsonIgnore
    public void emprunter() throws Exception{

        if (!empruntable) {
            throw new Exception("Document non empruntable" + this);
        }
        if (emprunte) {
            throw new Exception("Deja Emprunte"+ this);
        }
        emprunte = true;
        nbEmprunt++;

    }

    /**
     * Retourne vrai si le document est emprunte.
     */

    @JsonIgnore
    public boolean estEmprunte() { return emprunte; }

    /**
     * <TT>restituer</TT> est appelee lors de la restitution d'un
     * document. La localisation ou ranger le document est affichee.
     */


    public void restituer() throws Exception {
        if(!empruntable){
            throw new Exception("Impossible de restituer un document non empruntable");
        }
        if(!emprunte){
            throw new Exception("Impossible de restituer un document non emprunte");
        }
        emprunte = false;
    }

}
