package com.mediatheque.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ghiles FEGHOUL
 * @Date 26/01/2018
 * @Licence MIT
 */
@Entity(name = "localizations")
public class Localisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String salle;

    private String rayon;

    public Localisation() {
        documents = new ArrayList<Document>();
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "localization",cascade = CascadeType.ALL)
    private List<Document> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
