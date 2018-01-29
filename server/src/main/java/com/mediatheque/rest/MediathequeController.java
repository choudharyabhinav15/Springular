package com.mediatheque.rest;

import com.mediatheque.model.Mediatheque;
import com.mediatheque.service.impl.MediathequeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MediathequeController {

    @Autowired
    private MediathequeServiceImpl mediathequeService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/media")
    public ResponseEntity<?> addMedia(@RequestBody Mediatheque mediatheque){
        return new ResponseEntity<Mediatheque>(mediathequeService.save(mediatheque), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/media")
    public ResponseEntity<?> updateMedia(@RequestBody Mediatheque mediatheque){
        return new ResponseEntity<Mediatheque>(mediathequeService.update(mediatheque), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/media/{id}")
    public ResponseEntity<Map> deleteMedia(@PathVariable Long id){
        mediathequeService.remove(id);
        HashMap<String, String> result = new HashMap<>();
        result.put("result","success");
        return ResponseEntity.accepted().body(result);
    }


}
