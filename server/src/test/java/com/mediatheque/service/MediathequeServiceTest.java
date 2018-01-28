package com.mediatheque.service;

import com.mediatheque.AbstractTest;
import com.mediatheque.model.Mediatheque;
import com.mediatheque.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;

/**
 * @author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public class MediathequeServiceTest extends AbstractTest {

    @Autowired
    UserService userService;

    @Autowired
    MediathequeService mediathequeService;

    @Test(expected = AccessDeniedException.class)
    public void testAddMediaWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        Mediatheque mediatheque = this.buildMedia();
        mediathequeService.save(mediatheque);
    }

    @Test(expected = AccessDeniedException.class)
    public void testAddMediaWithoutUser() throws AccessDeniedException{
        mediathequeService.save(buildMedia());
    }

    @Test
    public void testAddMediaWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Mediatheque mediatheque = buildMedia();
        mediathequeService.save(mediatheque);
        Mediatheque media = mediathequeService.find(mediatheque.getId());
        Assert.assertEquals(mediatheque.getId(),media.getId());
        Assert.assertEquals(mediatheque.getName(), media.getName());
    }

    @Test
    public void testFindMediaWithoutUser() throws AccessDeniedException{
        Mediatheque mediatheque = mediathequeService.find(1L);
        Assert.assertTrue(mediatheque.getId()==1);
    }

    @Test
    public void testFindMediaWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        Mediatheque mediatheque = mediathequeService.find(1L);
        Assert.assertTrue(mediatheque.getId()==1);
    }

    @Test
    public void testFindMediaWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Mediatheque mediatheque = mediathequeService.find(1L);
        Assert.assertTrue(mediatheque.getId()==1);
    }

    @Test
    public void testFindAllMediaWithoutUser() throws AccessDeniedException{
        List<Mediatheque> liste = mediathequeService.findAll();
        Assert.assertTrue(!liste.isEmpty());
        Mediatheque mediatheque = liste.get(0);
        Assert.assertTrue(mediatheque.getId().equals(liste.get(0).getId()));
    }

    @Test
    public void testFindAllMediaWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        List<Mediatheque> liste = mediathequeService.findAll();
        Assert.assertTrue(!liste.isEmpty());
        Mediatheque mediatheque = liste.get(0);
        Assert.assertTrue(mediatheque.getId().equals(liste.get(0).getId()));
    }

    @Test
    public void testFindAllMediaWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        List<Mediatheque> liste = mediathequeService.findAll();
        Assert.assertTrue(!liste.isEmpty());
        Mediatheque mediatheque = liste.get(0);
        Assert.assertTrue(mediatheque.getId().equals(liste.get(0).getId()));
    }

    @Test
    public void testUpdateMediaWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Mediatheque mediatheque = mediathequeService.find(1L);
        mediatheque.setName("Test Media Updating");
        mediathequeService.update(mediatheque);
        Mediatheque media = mediathequeService.find(1L);
        Assert.assertEquals(media.getId(),mediatheque.getId());
        Assert.assertEquals(media.getName(), mediatheque.getName());
        Assert.assertTrue(media.getName().equals("Test Media Updating"));
    }

    @Test(expected = AccessDeniedException.class)
    public void testDeleteMediaWithoutUser() throws AccessDeniedException{
        mediathequeService.remove(1L);
    }

    @Test(expected = AccessDeniedException.class)
    public void testDeleteMediaWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        mediathequeService.remove(1L);
    }


    //<editor-fold desc="Ce test doit être exécuté à part après exécution de tous les test précédents">
//    @Test
//    public void testDeleteMediaWithAdmin() throws AccessDeniedException, InterruptedException {
//        mockAuthenticatedUser(buildTestAdmin());
//        Mediatheque mediatheque = mediathequeService.find(1L);
//        if(mediatheque!=null)
//            mediathequeService.remove(1L);
//        mediatheque = mediathequeService.find(1L);
//        Assert.assertTrue(mediatheque==null);
//    }
    //</editor-fold>

}
