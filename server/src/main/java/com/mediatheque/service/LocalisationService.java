package com.mediatheque.service;

import com.mediatheque.model.Localisation;

import java.util.List;

public interface LocalisationService {

    Localisation find(Long id);

    Localisation save(Localisation localisation);

    Localisation update(Localisation localisation);

    List<Localisation> findAll();

    void remove(Long id);


}
