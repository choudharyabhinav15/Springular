package com.mediatheque.rest;

import com.mediatheque.model.*;
import com.mediatheque.repository.AudioRepository;
import com.mediatheque.repository.GameRepository;
import com.mediatheque.repository.LivreRepository;
import com.mediatheque.repository.VideoRepository;
import com.mediatheque.service.impl.DocumentServiceImpl;
import com.mediatheque.service.impl.LocalisationServiceImpl;
import com.mediatheque.service.impl.MediathequeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Ghiles FEGHOUL on 28/12/2017
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class PublicController {

    @Autowired
    private DocumentServiceImpl documentService;

    @Autowired
    private MediathequeServiceImpl mediathequeService;


    @Autowired
    private LocalisationServiceImpl localisationService;

    @Autowired
    AudioRepository audioRepository;

    @Autowired
    LivreRepository livreRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/localization/all")
    public List<Localisation> getLocalisations(){
        return localisationService.findAll();
    }


    @RequestMapping(method = RequestMethod.GET,value = "/document/{id}")
    public Document getDocument(@PathVariable Long id){
        return documentService.find(id);
    }


    @RequestMapping(method = GET, value = "/document/all")
    public List<Livre> index(){
        return livreRepository.findAll();
    }

    @GetMapping("/medias")
    public List<Mediatheque> getAllMedia(){
        return mediathequeService.findAll();
    }

    @GetMapping("/media/{id}")
    public Mediatheque getMedia(@PathVariable Long id){
        return mediathequeService.find(id);
    }

}
