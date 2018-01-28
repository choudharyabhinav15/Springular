package com.mediatheque.service.impl;

import com.mediatheque.model.Localisation;
import com.mediatheque.repository.LocalisationRepository;
import com.mediatheque.service.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
@Service
public class LocalisationServiceImpl implements LocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    @Override
    public Localisation find(Long id) {
        return localisationRepository.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Localisation save(Localisation localisation) {
        return localisationRepository.save(localisation);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Localisation update(Localisation localisation) {
        return localisationRepository.saveAndFlush(localisation);
    }

    @Override
    public List<Localisation> findAll() {
        return localisationRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void remove(Long id) {
        localisationRepository.delete(id);
    }
}
