package com.mediatheque.model;

import javax.persistence.Entity;

/**
 * @Author Ghiles FEGHOUL
 * @Date 26/01/2018
 * @Licence MIT
 */
@Entity(name = "games")
public class Game extends Document{

    private static final int duree = 4*7;

    private final double tarif = 2.0;

    public int getStat(){
        return nbEmpruntTotal;
    }

    @Override
    public int dureeEmprunt() {
        return 0;
    }

    @Override
    public double tarifEmprunt() {
        return 0;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEmpruntable() {
        return empruntable;
    }

    public void setEmpruntable(boolean empruntable) {
        this.empruntable = empruntable;
    }

    public boolean isEmprunte() {
        return emprunte;
    }

    public void setEmprunte(boolean emprunte) {
        this.emprunte = emprunte;
    }

    public int getNbEmprunt() {
        return nbEmprunt;
    }

    public void setNbEmprunt(int nbEmprunt) {
        this.nbEmprunt = nbEmprunt;
    }

    public Localisation getLocalization() {
        return localization;
    }

    public void setLocalization(Localisation localization) {
        this.localization = localization;
    }

    public int getNbEmpruntTotal() {
        return nbEmpruntTotal;
    }

    public void setNbEmpruntTotal(int nbEmpruntTotal) {
        this.nbEmpruntTotal = nbEmpruntTotal;
    }
}
