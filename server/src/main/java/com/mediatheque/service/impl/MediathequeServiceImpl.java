package com.mediatheque.service.impl;

import com.mediatheque.model.Mediatheque;
import com.mediatheque.repository.MediathequeRepository;
import com.mediatheque.service.MediathequeService;
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
public class MediathequeServiceImpl implements MediathequeService {

    @Autowired
    private MediathequeRepository mediathequeRepository;

    @Override
    public Mediatheque find(Long id) {
        return mediathequeRepository.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Mediatheque save(Mediatheque mediatheque) {
        return mediathequeRepository.save(mediatheque);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Mediatheque update(Mediatheque mediatheque) {
        return mediathequeRepository.saveAndFlush(mediatheque);
    }

    @Override
    public List<Mediatheque> findAll() {
        return mediathequeRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void remove(Long id) {
        mediathequeRepository.delete(id);
    }
}
