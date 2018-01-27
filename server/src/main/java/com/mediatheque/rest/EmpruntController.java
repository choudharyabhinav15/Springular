package com.mediatheque.rest;

import com.mediatheque.service.impl.DocumentServiceImpl;
import com.mediatheque.service.impl.FicheEmpruntServiceImpl;
import com.mediatheque.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class EmpruntController {

     @Autowired
     private FicheEmpruntServiceImpl ficheEmpruntService;

     @Autowired
     private UserServiceImpl userService;

     @Autowired
     private DocumentServiceImpl documentService;


}
