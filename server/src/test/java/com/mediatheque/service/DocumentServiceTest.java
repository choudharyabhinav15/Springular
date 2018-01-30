package com.mediatheque.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.mediatheque.AbstractTest;
import com.mediatheque.model.Document;
import com.mediatheque.model.Localisation;
import com.mediatheque.model.Mediatheque;
import com.mediatheque.service.impl.DocumentServiceImpl;
import com.mediatheque.service.impl.LocalisationServiceImpl;

/**
 * @Author Juba TIDAF
 * @Date 28/01/2018
 */
public class DocumentServiceTest extends AbstractTest {
	@Autowired
    UserService userService;
	
	@Autowired
	DocumentServiceImpl documentService;
	
	@Autowired
	LocalisationServiceImpl localisationService;

	@Autowired
    MediathequeService mediathequeService;

	@Test(expected = AccessDeniedException.class)
    public void testAddDocuementWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        localisationService.save(buildLocalization());
        mediathequeService.save(buildMedia());
        Document document = this.buildLivre();
        documentService.save(document);
    }
	
	@Test(expected = AccessDeniedException.class)
    public void testAddDocumentWithoutUser() throws AccessDeniedException{
		localisationService.save(buildLocalization());
		mediathequeService.save(buildMedia());
		documentService.save(buildLivre());
    }
	
	@Test
    public void testAddDocumentWithAdmi() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        localisationService.save(buildLocalization());
        mediathequeService.save(buildMedia());
        Document document = buildLivre();
        documentService.save(document);
        Document doc = documentService.find(document.getId());
        Assert.assertEquals(document.getId(),doc.getId());
        Assert.assertEquals(document.getTitle(), doc.getTitle());
    }

    @Test
    public void testFindDocumentWithoutUser() throws AccessDeniedException{
    	Document document = documentService.find(1L);
        Assert.assertTrue(document.getId()==1);
    }
	
    @Test
    public void testFindDocumentWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        Document document = documentService.find(1L);
        Assert.assertTrue(document.getId()==1);
    }

    @Test
    public void testFindDocumentWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Document document = documentService.find(1L);
        Assert.assertTrue(document.getId()==1);
    }
	
    @Test
    public void testFindAllDocumentWithoutUser() throws AccessDeniedException{
        List<Document> liste = documentService.findAll();
        Assert.assertTrue(!liste.isEmpty());
        Document document = liste.get(0);
        Assert.assertTrue(document.getId().equals(liste.get(0).getId()));
    }

    @Test
    public void testFindAllDocumentWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        List<Document> liste = documentService.findAll();
        System.out.println(liste.get(0).getTitle());
        Assert.assertTrue(!liste.isEmpty());
        Document document = liste.get(0);
        Assert.assertTrue(document.getId().equals(liste.get(0).getId()));
    }
    
    @Test
    public void testFindAllDocumentWithUserr() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        List<Document> liste = documentService.findAll();
        Assert.assertTrue(!liste.isEmpty());
        Document document = liste.get(0);
        Assert.assertTrue(document.getId().equals(liste.get(0).getId()));
    }

    
    
    @Test(expected = AccessDeniedException.class)
    public void testDeleteDocumentWithoutUser() throws AccessDeniedException{
    	documentService.remove(1L);
    }

    @Test(expected = AccessDeniedException.class)
    public void testDeleteDocumentWithUser() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestUser());
        documentService.remove(1L);
    }
        
    @Test
    public void testUpdateDocumentWithAdminn() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        Document document = documentService.find(1L);
        document.setTitle("Update document Test");
        documentService.update(document);
        Document doc = documentService.find(1L);
        Assert.assertEquals(doc.getId(),document.getId());
        Assert.assertEquals(doc.getTitle(), document.getTitle());
        Assert.assertTrue(doc.getTitle().equals("Update document Test"));
    }
  /* 
    @Test
    public void findDocumentByLocalizationWithAdmin() throws AccessDeniedException{
    	mockAuthenticatedUser(buildTestAdmin());
    	Localisation localisation=buildLocalization();
    	localisationService.save(localisation);
    	//Document document1=buildLivre();
    	documentService.save(buildLivre());
    	//Localisation localisationfind = new Localisation();
    	//localisation.setId(1L);
    	//localisation.setSalle("2");
    	//localisation.setRayon("Litterature");
    	//document1.setLocalization(localisationfind);
    	//documentService.save(document1);
    	//Assert.assertTrue(!(document1==null));
    	List<Document> liste=documentService.findDocumentsByLocalization(localisation);
    	Assert.assertTrue(!liste.isEmpty());
        Document document = liste.get(0);
        Assert.assertTrue(document.getLocalization().equals(liste.get(0).getLocalization()));
    }  */
}
