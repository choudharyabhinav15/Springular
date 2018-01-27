package com.mediatheque.rest;

import com.mediatheque.model.Localisation;
import com.mediatheque.service.impl.LocalisationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalisationController {

    @Autowired
    private LocalisationServiceImpl localisationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/localization")
    public ResponseEntity<?> addLocalization(@RequestBody Localisation localisation){
        return new ResponseEntity<Localisation>(localisationService.save(localisation), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/localization")
    public ResponseEntity<?> updateLocalization(@RequestBody Localisation localisation){
        /*Localisation l = localisationService.find(localisation.getId());
        l.setRayon(localisation.getRayon());
        l.setSalle(localisation.getSalle());*/
        return new ResponseEntity<Localisation>(localisationService.update(localisation), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/localization/{id}")
    public Localisation getLocalization(@PathVariable Long id){
        return localisationService.find(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/localization/{id}")
    public ResponseEntity<Map> deleteDocument(@PathVariable Long id){
        localisationService.remove(id);
        HashMap<String, String> result = new HashMap<>();
        result.put("result","success");
        return ResponseEntity.accepted().body(result);
    }


}
