package com.mediatheque.model;

import com.mediatheque.util.Dateutil;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "emprunts")
public class FicheEmprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade= CascadeType.ALL)
    private Mediatheque mediatheque;

    @ManyToOne(cascade = CascadeType.ALL)
    private User client;

    @OneToOne(fetch = FetchType.EAGER)
    private Document document;

    @OneToOne(fetch = FetchType.EAGER)
    private Rappel rappel;

    private Date dateEmprunt;

    private Date dateLimite;

    private boolean depasse;

    private static int nbEmpruntsTotal = 0;

    public FicheEmprunt(Mediatheque m, User c, Document d) throws Exception{
        mediatheque = m;
        client = c;
        document = d;
        dateEmprunt = Dateutil.dateDuJour();
        int duree = document.dureeEmprunt();
        dateLimite = client.dateRetour(dateEmprunt, duree);
        depasse = false;
        document.emprunter();
        client.emprunter(this);
        nbEmpruntsTotal++;
        System.out.println("\tTarif = " + getTarifEmprunt() + " euros");
    }

    /**
     * <TT>verifier</TT> teste si une lettre a ete deja envoyee.
     * Dans ce cas, on relance un rappel
     * sinon on teste si la date est maintenant depassee,
     * si oui on envoie un premier rappel
     */
    public void verifier() throws Exception {
        if (depasse) {
            relancer();
        } else {
            Date dateActuelle = Dateutil.dateDuJour();
            if (dateLimite.before(dateActuelle)) {
                premierRappel();
            }
        }
    }

    /**
     * <TT>premierRappel</TT>
     * Le client est marque ; la lettre de rappel est envoyee
     */
    private void premierRappel() throws Exception{
        depasse = true;
        client.marquer();
        rappel = new Rappel(mediatheque.getName(), this);
    }

    /**
     * <TT>relancer</TT> verifie si l'emprunt est depasse, auquel cas
     * il faudra relancer le client retardataire.
     */
    private void relancer() {
        Date dateActuelle = Dateutil.dateDuJour();
        if (depasse && rappel != null) {
            Date dateRelance = Dateutil.addDate(rappel.getDateRappel(), 7);
            if (dateRelance.before(dateActuelle)) {
                rappel.relancer();
            }
        }
        return;
    }


    /**
     * <TT>correspond</TT> verifie que l'emprunt correspond au document et
     * au client en retournant vrai.
     *   @param cli Emprunteur
     *   @param doc Document emprunte
     *   @return Vrai si l'emprunt correspond
     */
    public boolean correspond(User cli, Document doc) {
        return (client.equals(cli) && document.equals(doc));
    }

    /**
     * <TT>restituer</TT> est lancee lors de la restitution d'un document.
     * Elle appelle les methodes de restitution sur le document et le client.
     */
    public void restituer() throws Exception{
        client.restituer(this);
        document.restituer();
    }

    public User getClient() {
        return client;
    }

    public Document getDocument() {
        return document;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public boolean getDepasse() {
        return depasse;
    }

    public int getDureeEmprunt(){
        return (int) ((dateLimite.getTime () - dateEmprunt.getTime ()) / (1000 * 60 * 60 * 24));
    }

    public double getTarifEmprunt(){
        double tarifNominal = document.tarifEmprunt();
        return  client.sommeDue(tarifNominal);
    }


    /**
     *<TT>afficherStatistiques</TT> affiche le nombre total d'emprunts.
     */
    public static void afficherStatistiques() {
        System.out.println("Nombre total d'emprunts = " + nbEmpruntsTotal);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mediatheque getMediatheque() {
        return mediatheque;
    }

    public void setMediatheque(Mediatheque mediatheque) {
        this.mediatheque = mediatheque;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Rappel getRappel() {
        return rappel;
    }

    public void setRappel(Rappel rappel) {
        this.rappel = rappel;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public boolean isDepasse() {
        return depasse;
    }

    public void setDepasse(boolean depasse) {
        this.depasse = depasse;
    }

    public static int getNbEmpruntsTotal() {
        return nbEmpruntsTotal;
    }

    public static void setNbEmpruntsTotal(int nbEmpruntsTotal) {
        FicheEmprunt.nbEmpruntsTotal = nbEmpruntsTotal;
    }
}
