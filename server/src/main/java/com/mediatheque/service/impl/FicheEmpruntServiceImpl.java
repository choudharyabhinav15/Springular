package com.mediatheque.service.impl;

import com.mediatheque.model.FicheEmprunt;
import com.mediatheque.repository.FicheEmpruntRepository;
import com.mediatheque.service.FicheEmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FicheEmpruntServiceImpl implements FicheEmpruntService {

    @Autowired
    private FicheEmpruntRepository ficheEmpruntRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public FicheEmprunt find(Long id) {
        return ficheEmpruntRepository.findOne(id);
    }

    @Override
    public FicheEmprunt save(FicheEmprunt ficheEmprunt) {
        return ficheEmpruntRepository.save(ficheEmprunt);
    }

    @Override
    public FicheEmprunt update(FicheEmprunt ficheEmprunt) {
        return ficheEmpruntRepository.saveAndFlush(ficheEmprunt);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<FicheEmprunt> findAll() {
        return ficheEmpruntRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void remove(Long id) {
        ficheEmpruntRepository.delete(id);
    }
}
