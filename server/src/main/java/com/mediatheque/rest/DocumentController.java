package com.mediatheque.rest;


import com.mediatheque.model.Document;
import com.mediatheque.model.Livre;
import com.mediatheque.repository.LivreRepository;
import com.mediatheque.service.impl.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

    @Autowired
    private DocumentServiceImpl documentService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/document")
    public ResponseEntity<?> addDocument(@RequestBody Livre document){
        return new ResponseEntity<Document>(documentService.save(document), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/document")
    public ResponseEntity<?> updateDocument(@RequestBody Livre document){
        /*
        Document doc = documentService.find(document.getId());
        doc.setTitle(document.getTitle());
        doc.setYear(document.getYear());
        doc.setAuthor(document.getAuthor());
        doc.setCode(document.getCode());
        if(!doc.getLocalization().equals(document.getLocalization()))
            doc.setLocalization(document.getLocalization());*/
        return new ResponseEntity<Document>(documentService.update(document), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/document/{id}")
    public ResponseEntity<Map> deleteDocument(@PathVariable Long id){
        documentService.remove(id);
        HashMap<String, String> result = new HashMap<>();
        result.put("result","success");
        return ResponseEntity.accepted().body(result);
    }

}
