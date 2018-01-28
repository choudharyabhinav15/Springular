package com.mediatheque.service;

import com.mediatheque.model.Localisation;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface LocalisationService {

    Localisation find(Long id);

    Localisation save(Localisation localisation);

    Localisation update(Localisation localisation);

    List<Localisation> findAll();

    void remove(Long id);


}
