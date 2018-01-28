package com.mediatheque.service;

import com.mediatheque.AbstractTest;
import com.mediatheque.model.*;
import com.mediatheque.util.Dateutil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import java.util.Date;

/**
 * @author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public class FicheEmpruntServiceTest extends AbstractTest {

    @Autowired
    UserService userService;

    @Autowired
    MediathequeService mediathequeService;

    @Autowired
    FicheEmpruntService ficheEmpruntService;

    @Autowired
    DocumentService documentService;

    @Autowired
    RappelService rappelService;

    @Test(expected = AccessDeniedException.class)
    public void testEmpruntWithoutUser() throws AccessDeniedException, Exception{
        Document document = buildLivre();
        documentService.save(buildLivre());

        User user1 = userService.findById(2L);

        FicheEmprunt ficheEmprunt = new FicheEmprunt(buildMedia(),user1,document);
        ficheEmpruntService.save(ficheEmprunt);
        userService.update(user1);
    }

    @Test
    public void testEmpruntWithUser() throws Exception {
        mockAuthenticatedUser(buildTestUser());

        Document document = buildLivre();
        documentService.save(buildLivre());

        User user1 = userService.findById(2L);
        int loans = user1.getLoans();
        int current = user1.getCurrent();

        FicheEmprunt ficheEmprunt = new FicheEmprunt(buildMedia(),user1,document);
        ficheEmpruntService.save(ficheEmprunt);
        userService.update(user1);

        FicheEmprunt fiche = ficheEmpruntService.find(1L);
        document = fiche.getDocument();
        Assert.assertTrue(document.estEmprunte());
        Assert.assertTrue(!fiche.getClient().getLesEmprunts().isEmpty());
        Assert.assertTrue(fiche.getClient().getLesEmprunts().get(0).getDocument().getId().equals(document.getId()));
        Assert.assertTrue(fiche.getClient().getId().equals(2L));
        Assert.assertTrue(fiche.getClient().getLoans()==loans+1);
        Assert.assertTrue(fiche.getClient().getCurrent()==current+1);
    }

    @Test
    public void testEmpruntWithAdmin() throws Exception {
        mockAuthenticatedUser(buildTestAdmin());

        Document document = buildLivre();
        documentService.save(buildLivre());

        User user1 = userService.findById(2L);
        int loans = user1.getLoans();
        int current = user1.getCurrent();

        FicheEmprunt ficheEmprunt = new FicheEmprunt(buildMedia(),user1,document);
        ficheEmpruntService.save(ficheEmprunt);
        userService.update(user1);

        FicheEmprunt fiche = ficheEmpruntService.find(1L);
        document = fiche.getDocument();
        Assert.assertTrue(document.estEmprunte());
        Assert.assertTrue(!fiche.getClient().getLesEmprunts().isEmpty());
        Assert.assertTrue(fiche.getClient().getLesEmprunts().get(0).getDocument().getId().equals(document.getId()));
        Assert.assertTrue(fiche.getClient().getId().equals(2L));
        Assert.assertTrue(fiche.getClient().getLoans()==loans+1);
        Assert.assertTrue(fiche.getClient().getCurrent()==current+1);
    }

    @Test
    public void testRestitutionWithRegularUser() throws AccessDeniedException, Exception{
        testEmpruntWithAdmin();
        FicheEmprunt ficheEmprunt = ficheEmpruntService.find(1L);
        User user1 = ficheEmprunt.getClient();
        int current = user1.getCurrent();
        Document document = ficheEmprunt.getDocument();
        ficheEmprunt.restituer();
        ficheEmpruntService.update(ficheEmprunt);
        userService.update(user1);
        documentService.save(document);
        user1 = userService.findById(2L);
        Assert.assertTrue(user1.getCurrent()==current-1);
        Assert.assertTrue(!ficheEmprunt.getDocument().estEmprunte());
    }

    @Test
    public void testRappelWithAdmin() throws AccessDeniedException, Exception{
        testEmpruntWithAdmin();
        FicheEmprunt ficheEmprunt = ficheEmpruntService.find(1L);
        ficheEmprunt.verifier();
        Assert.assertTrue(!ficheEmprunt.isDepasse());
    }

    @Test
    public void testIrregularClientRappelWithAdmin() throws AccessDeniedException, Exception {
        testEmpruntWithAdmin();
        FicheEmprunt ficheEmprunt = ficheEmpruntService.find(1L);
        User client = ficheEmprunt.getClient();
        int outdated = client.getOutdate_loan();
        System.out.println(outdated+"\n");
        Date date = ficheEmprunt.getDateLimite();
        Date dateLimite = Dateutil.addDate(date,-31);
        System.out.println(dateLimite+"\n");
        ficheEmprunt.setDateLimite(dateLimite);
        ficheEmprunt.verifier();
        Assert.assertTrue(ficheEmprunt.isDepasse());
        Assert.assertTrue(ficheEmprunt.getClient().getOutdate_loan()== outdated+1);
        Assert.assertTrue(!ficheEmprunt.getClient().peutEmprunter());
    }

    @Test(expected = java.lang.AssertionError.class)
    public void testEmpruntWithIrregularUser() throws java.lang.AssertionError, Exception {
        testEmpruntWithAdmin();
        FicheEmprunt ficheEmprunt = ficheEmpruntService.find(1L);
        User client = ficheEmprunt.getClient();
        int outdated = client.getOutdate_loan();
        System.out.println(outdated+"\n");
        Date date = ficheEmprunt.getDateLimite();
        Date dateLimite = Dateutil.addDate(date,-31);
        System.out.println(dateLimite+"\n");
        ficheEmprunt.setDateLimite(dateLimite);
        ficheEmprunt.verifier();
        Assert.assertTrue(ficheEmprunt.isDepasse());
        Assert.assertTrue(ficheEmprunt.getClient().getOutdate_loan()== outdated+1);
        Assert.assertTrue(!ficheEmprunt.getClient().peutEmprunter());
        client.emprunter(ficheEmprunt);
    }

}
