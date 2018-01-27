package com.mediatheque.service.impl;

import com.mediatheque.model.Localisation;
import com.mediatheque.repository.LocalisationRepository;
import com.mediatheque.service.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalisationServiceImpl implements LocalisationService {

    @Autowired
    private LocalisationRepository localisationRepository;

    @Override
    public Localisation find(Long id) {
        return localisationRepository.findOne(id);
    }

    @Override
    public Localisation save(Localisation localisation) {
        return localisationRepository.save(localisation);
    }

    @Override
    public Localisation update(Localisation localisation) {
        return localisationRepository.saveAndFlush(localisation);
    }

    @Override
    public List<Localisation> findAll() {
        return localisationRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        localisationRepository.delete(id);
    }
}
