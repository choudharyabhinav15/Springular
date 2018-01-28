package com.mediatheque.rest;

import com.mediatheque.model.Document;
import com.mediatheque.model.FicheEmprunt;
import com.mediatheque.model.Mediatheque;
import com.mediatheque.model.User;
import com.mediatheque.service.FicheEmpruntService;
import com.mediatheque.service.impl.DocumentServiceImpl;
import com.mediatheque.service.impl.FicheEmpruntServiceImpl;
import com.mediatheque.service.impl.MediathequeServiceImpl;
import com.mediatheque.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class EmpruntController {

     @Autowired
     private FicheEmpruntServiceImpl ficheEmpruntService;

     @Autowired
     private UserServiceImpl userService;

     @Autowired
     private DocumentServiceImpl documentService;

     @Autowired
     private MediathequeServiceImpl mediathequeService;

     @PreAuthorize("hasRole('USER')")
     @PostMapping("/emprunt/[idUser}/{idDoc}/{idMedia}")
     public ResponseEntity<FicheEmprunt> addFiche(@PathVariable Long idUser, @PathVariable Long idDoc, @PathVariable Long idMedia) throws Exception {
          User user = userService.findById(idUser);
          Document document = documentService.find(idDoc);
          Mediatheque mediatheque = mediathequeService.find(idMedia);
          FicheEmprunt ficheEmprunt = new FicheEmprunt(mediatheque,user,document);
          ficheEmpruntService.save(ficheEmprunt);
          userService.update(user);
          return new ResponseEntity<FicheEmprunt>(ficheEmprunt, HttpStatus.CREATED);
     }

}
