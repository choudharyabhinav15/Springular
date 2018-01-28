package com.mediatheque.service;

import com.mediatheque.AbstractTest;
import com.mediatheque.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

public class FicheEmpruntServiceTest extends AbstractTest {

    @Autowired
    UserService userService;

    @Autowired
    MediathequeService mediathequeService;

    @Autowired
    FicheEmpruntService ficheEmpruntService;

    @Autowired
    DocumentService documentService;

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
        Assert.assertTrue(!fiche.getClient().getLesEmprunts().isEmpty());
        Assert.assertTrue(fiche.getClient().getLesEmprunts().get(0).getDocument().getId().equals(document.getId()));
        Assert.assertTrue(fiche.getClient().getId().equals(2L));
        Assert.assertTrue(fiche.getClient().getLoans()==loans+1);
        Assert.assertTrue(fiche.getClient().getCurrent()==current+1);
    }



}
