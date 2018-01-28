package com.mediatheque.service;

import com.mediatheque.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
/**
 * @author Juba TIDAF
 * @Date 27/01/2018
 * @Licence MIT
 */
public class DocumentServiceTest extends AbstractTest {

    @Autowired
    DocumentService documentService;

    @Autowired
    LocalisationService localisationService;

    @Test
    public void testAddDocumentWithAdmin() throws AccessDeniedException{
        mockAuthenticatedUser(buildTestAdmin());
        localisationService.save(buildLocalization());
        documentService.save(buildLivre());
    }


}
