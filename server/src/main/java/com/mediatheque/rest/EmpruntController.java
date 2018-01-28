package com.mediatheque.rest;

import com.mediatheque.model.*;
import com.mediatheque.service.FicheEmpruntService;
import com.mediatheque.service.impl.*;
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

     @Autowired
     private RappelServiceImpl rappelService;

     @PreAuthorize("hasRole('USER')")
     @GetMapping("/emprunt/[idUser}/{idDoc}/{idMedia}")
     public ResponseEntity<FicheEmprunt> addFiche(@PathVariable Long idUser, @PathVariable Long idDoc, @PathVariable Long idMedia) throws Exception {
          User user = userService.findById(idUser);
          Document document = documentService.find(idDoc);
          Mediatheque mediatheque = mediathequeService.find(idMedia);
          FicheEmprunt ficheEmprunt = new FicheEmprunt(mediatheque,user,document);
          ficheEmpruntService.save(ficheEmprunt);
          userService.update(user);
          return new ResponseEntity<FicheEmprunt>(ficheEmprunt, HttpStatus.CREATED);
     }

     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/emprunt/restitution/{id}")
     public ResponseEntity<Map> restituerEmprunt(@PathVariable Long id) throws Exception {
          FicheEmprunt ficheEmprunt = ficheEmpruntService.find(id);
          User client = ficheEmprunt.getClient();
          Document document = ficheEmprunt.getDocument();
          ficheEmprunt.restituer();
          ficheEmpruntService.update(ficheEmprunt);
          userService.update(client);
          documentService.update(document);
          Map<String, String> result = new HashMap<>();
          result.put("result", "success");
          return ResponseEntity.accepted().body(result);
     }

     @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/emprunt/rappel/{id}")
     public ResponseEntity<Rappel> verifier(@PathVariable Long id) throws Exception {
          FicheEmprunt ficheEmprunt = ficheEmpruntService.find(id);
          User client = ficheEmprunt.getClient();
          ficheEmprunt.verifier();
          if(ficheEmprunt.getRappel()!=null){
               rappelService.save(ficheEmprunt.getRappel());
          }
          ficheEmpruntService.update(ficheEmprunt);
          userService.update(client);
          return new ResponseEntity<Rappel>(ficheEmprunt.getRappel(), HttpStatus.CREATED);
     }
}
