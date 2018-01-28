package com.mediatheque.model;

import com.mediatheque.util.Dateutil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * @Author Ghiles FEGHOUL
 * @Date 26/01/2018
 * @Licence MIT
 */
@Entity(name = "rappels")
public class Rappel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final static String DEFAUT =
            "Tout emprunt est desormais impossible\n";
    private final static String dashLine = "\n----------------------------------------------------\n";
    private String nomMedia, entete, corps, fin;

    /**
     * Date d'envoi de la lettre
     */
    private Date dateRappel;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FicheEmprunt enRetard;

    // Les methodes
    /**
     * Constructeur de lettre de rappel avec le corps minimal
     * le constructeur fait un affichage de la lettre de rappel
     */
    ///CORRECTION
    Rappel(String nomMedia, FicheEmprunt emprunt) {
        dateRappel = emprunt.getDateLimite();
        this.nomMedia = nomMedia;
        this.enRetard = emprunt;
        User cl= enRetard.getClient();
        Document doc = enRetard.getDocument();
        entete = "\n\tA Monsieur " + cl.getLastname() + " " + cl.getFirstname() +
                "\n\t" + cl.getAddress() +
                "\n\tObjet: Lettre de rappel\n\n" +
                "Document non restitue le " +
                Dateutil.dateToString(dateRappel) + " : \n\t" +
                doc.getId() + ": \"" + doc.getTitle() +
                "\" de " + doc.getAuthor();
        corps = DEFAUT;
        fin = "\n\tLe chef,\n\n" + dashLine;
        System.out.println(debut());
        System.out.println(corps);
        System.out.println(fin);
    }

    /**
     * <TT>relancer</TT> renouvelle le rappel
     * (affiche de nouveau la lettre de rappel)
     */
    //CORRECTION
    public void relancer() {
        dateRappel = Dateutil.dateDuJour();
        System.out.println(debut());
        System.out.println("Rappel : ");
        System.out.println(corps);
        System.out.println(fin);
    }

    /**
     * Debut de la lettre entete + nom de la mediatheque + date
     */
    public String debut() {
        String deb = "Mediatheque \"" + nomMedia + "\"\tLe " +
                Dateutil.dateToString(Dateutil.dateDuJour());
        return dashLine + deb + entete ;
    }
    /**
     * <TT>getDateRappel</TT>
     * @return Date
     */
    public Date getDateRappel(){
        return dateRappel;
    }
}
