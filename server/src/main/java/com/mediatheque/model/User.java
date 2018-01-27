package com.mediatheque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.mediatheque.util.Dateutil;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Ghiles FEGHOUL on 27/12/2017
 */

@Entity
@Table(name = "USER")
public class User implements UserDetails, Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Email
  @Column(name="email", unique = true)
  private String email;

  @Column(name = "address")
  private String address;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name="inscription_date", nullable = true)
  private String inscription;

  @JsonIgnore
  @Column(name="max_loans")
  private int max_loans;

  @Column(name="loans")
  private int loans;

  @Column(name="current_loans")
  private int current;

  @Column(name = "outdated_loans")
  private int outdate_loan;

  public User() {
    this.current = 0;
    this.inscription = new DateTime().toString();
    this.max_loans = 24;
    this.loans = 0;
    lesEmprunts = new ArrayList<FicheEmprunt>();
  }

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_authority",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
  private List<Authority> authorities;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
  private List<FicheEmprunt> lesEmprunts;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getOutdate_loan() {
    return outdate_loan;
  }

  public void setOutdate_loan(int outdate_loan) {
    this.outdate_loan = outdate_loan;
  }

  public List<FicheEmprunt> getLesEmprunts() {
    return lesEmprunts;
  }

  public void setLesEmprunts(List<FicheEmprunt> lesEmprunts) {
    this.lesEmprunts = lesEmprunts;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getInscription() {
    return inscription;
  }

  public void setInscription(String inscription) {
    this.inscription = inscription;
  }

  public int getMax_loans() {
    return max_loans;
  }

  public void setMax_loans(int max_loans) {
    this.max_loans = max_loans;
  }

  public int getLoans() {
    return loans;
  }

  public void setLoans(int loans) {
    this.loans = loans;
  }

  public int getCurrent() {
    return current;
  }

  public void setCurrent(int current) {
    this.current = current;
  }

  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  // We can add the below fields in the users table.
  // For now, they are hardcoded.
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }

  /**
   * <TT>peutEmprunter</TT> verifie si le client peut emprunter le
   * document. Il peut le faire s'il n'a pas de document en retard de
   * restitution ou s'il n'a pas atteint son nombre maximal d'emprunt.
   * Si ce n'est pas le cas, elle retourne false.
   * <P>
   * Afin de distinguer les deux cas lors du retour, il serait
   * souhaitable de lever une exception : a faire dans la prochaine
   * version.
   *   @return vrai si l'emprunt est possible, faux sinon
   */
  public boolean peutEmprunter() {
    return outdate_loan <= 0 && current < max_loans;
  }


  /**
   * <TT>emprunter</TT> teste si le client peut emprunter le
   * document et leve une exception AssertionErrosi ce n'est pas
   * le cas
   * @throws Exception
   * @see #peutEmprunter()
   */
  public void emprunter(FicheEmprunt emprunt) throws Exception {
    assert peutEmprunter();
    lesEmprunts.add(emprunt);
    loans++;
    current++;
  }

  /**
   * <TT>emprunter</TT> pour version de client sans collection
   */
  public void emprunter() {
    assert peutEmprunter();
    loans++;
    current++;
  }

  /**
   * <TT>marquer</TT> interdit tout nouvel emprunt par le client.
   * Cette fonction est appelee par <TT>verifier</TT> de
   * la classe Emprunt.
   *   @see FicheEmprunt#verifier()
   */
  public void marquer() throws Exception {
    if(outdate_loan == current){
      throw new Exception("Impossible d'avoir plus d'emprunts en retard que d'emprunts : "+this);
    }
    outdate_loan++;
  }

  /**
   * <TT>restituer</TT> est appelee lors de la restitution d'un
   * document emprunte. S'il s'agissait d'un emprunt en retard
   * les mises a jour sont alors effectuees.
   *   @param emprunt fiche d'emprunt associee correspondante
   */
  public void restituer(FicheEmprunt emprunt) throws Exception {
    restituer(emprunt.getDepasse());
    lesEmprunts.remove(emprunt);
  }

  /**
   * <TT>restituer</TT> est appelee lors de la restitution d'un
   * document emprunte. S'il s'agissait d'un emprunt en retard
   * les mises a jour sont alors effectuees.
   *   @param enRetard Indique si l'emprunt est marque en retard
   */
  public void restituer(boolean enRetard) throws Exception {
    if(current == 0){
      throw new Exception("Restituer sans emprunt "+ this);
    }
    current--;
    if (enRetard) {
      if(outdate_loan == 0){
        throw new Exception("Restituer en retard sans retard "+ this);
      }
      outdate_loan--;
    }
  }

  /**
   * <TT>dateRetour</TT> retourne la date limite de restitution du
   * document emprunte a partir de la date du jour et de la
   * duree du pret.
   *   @param jour Date du pret (date du jour)
   *   @param duree Nombre de jours du pret
   *   @return Date limite de restitution du document
   */
  public Date dateRetour(Date jour, int duree) {
    duree = (int) ((double) duree * 1.8);
    return Dateutil.addDate(jour, duree);
  }

  /**
   * <TT>sommeDue</TT> permet de connaitre le tarif d'emprunt
   * d'un document selon le type de ce document et le type de client.
   * Le tarif pour un client a tarif normal est le tarif nominal, mais
   * on se reserve la possibilite d'evolution. On suppose que le reglement
   * est forcement effectue.
   *   @param tarif Tarif nominal de l'emprunt du document
   *   @return Tarif de l'emprunt
   */
  public double sommeDue(double tarif) {
    return tarif * 2.5;
  }

}
