package com.mediatheque.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.mediatheque.AbstractTest;
import com.mediatheque.model.Document;
import com.mediatheque.model.Localisation;
import com.mediatheque.service.impl.LocalisationServiceImpl;

/**
 * @Author Juba TIDAF
 * @Date 28/01/2018
 */
public class LocalizationServiceTest extends AbstractTest{
	@Autowired
	UserService userService;
	
	@Autowired
	LocalisationServiceImpl localisationService;

	@Test(expected = AccessDeniedException.class)
    public void testAddLocalizationWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        Localisation localisation = this.buildLocalization();
        localisationService.save(localisation);
    }
	
	@Test(expected = AccessDeniedException.class)
    public void testAddLocalizationWithoutUser() throws AccessDeniedException{
		localisationService.save(buildLocalization());
    }
	
	@Test
    public void testAddLocalizationWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Localisation localisation = this.buildLocalization();
        localisationService.save(localisation);
        Localisation loc = localisationService.find(localisation.getId());
        Assert.assertEquals(localisation.getId(),loc.getId());
        Assert.assertEquals(localisation.getRayon(), loc.getRayon());
    }
	
	@Test
    public void testFindLocalizationWithoutUser() throws AccessDeniedException{
		Localisation localisation = localisationService.find(1L);
        Assert.assertTrue(localisation.getId()==1);
    }
	
    @Test
    public void testFindLocalizationWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        Localisation localisation = localisationService.find(1L);
        Assert.assertTrue(localisation.getId()==1);
    }

    @Test
    public void testFindLocalizationWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Localisation localisation = localisationService.find(1L);
        Assert.assertTrue(localisation.getId()==1);
    }
    
    @Test
    public void testFindAllLocalizationWithoutUser() throws AccessDeniedException{
        List<Localisation> liste = localisationService.findAll();
        Assert.assertTrue(!liste.isEmpty());
        Localisation localisation = liste.get(0);
        Assert.assertTrue(localisation.getId().equals(liste.get(0).getId()));
    }

    
    @Test
    public void testFindAllLocalizationWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        List<Localisation> liste = localisationService.findAll();
        System.out.println(liste.get(0).getRayon());
        Assert.assertTrue(!liste.isEmpty());
        Localisation localisation = liste.get(0);
        Assert.assertTrue(localisation.getId().equals(liste.get(0).getId()));
    }
    @Test
    public void testFindAllLocalizationWithUsr() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        List<Localisation> liste = localisationService.findAll();
        System.out.println(liste.get(0).getRayon());
        Assert.assertTrue(!liste.isEmpty());
        Localisation localisation = liste.get(0);
        Assert.assertTrue(localisation.getId().equals(liste.get(0).getId()));
    }

    @Test
    public void testUpdateLocalizationWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Localisation localisation = localisationService.find(1L);
        localisation.setRayon("Rayon update Test");
        localisationService.update(localisation);
        Localisation doc = localisationService.find(1L);
        Assert.assertEquals(doc.getId(),localisation.getId());
        Assert.assertEquals(doc.getRayon(), localisation.getRayon());
        Assert.assertTrue(doc.getRayon().equals("Rayon update Test"));
    }
   
    
    @Test(expected = AccessDeniedException.class)
    public void testUpdateLocalizationWithUsr() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        Localisation localisation = localisationService.find(1L);
        localisation.setRayon("Rayon update Test");
        localisationService.update(localisation);
        Localisation doc = localisationService.find(1L);
        Assert.assertEquals(doc.getId(),localisation.getId());
        Assert.assertEquals(doc.getRayon(), localisation.getRayon());
        Assert.assertTrue(doc.getRayon().equals("Rayon update Test"));
    }
    
    @Test(expected = AccessDeniedException.class)
    public void testUpdateLocalizationWithoutUser() throws AccessDeniedException{
        Localisation localisation = localisationService.find(1L);
        localisation.setRayon("Rayon update Test");
        localisationService.update(localisation);
        Localisation doc = localisationService.find(1L);
        Assert.assertEquals(doc.getId(),localisation.getId());
        Assert.assertEquals(doc.getRayon(), localisation.getRayon());
        Assert.assertTrue(doc.getRayon().equals("Rayon update Test"));
    }
    
    @Test(expected = AccessDeniedException.class)
    public void testDeleteLocalizationWithoutUser() throws AccessDeniedException{
    	localisationService.remove(1L);
    }
   
    @Test(expected = AccessDeniedException.class)
    public void testDeleteLocalizationWithUsr() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        localisationService.remove(1L);
    }
    
}
