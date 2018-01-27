package com.mediatheque.service.impl;

import com.mediatheque.model.Rappel;
import com.mediatheque.repository.RappelRepository;
import com.mediatheque.service.RappelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RappelServiceImpl implements RappelService {

    @Autowired
    private RappelRepository rappelRepository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Rappel find(Long id) {
        return rappelRepository.findOne(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Rappel save(Rappel rappel) {
        return rappelRepository.save(rappel);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Rappel update(Rappel rappel) {
        return rappelRepository.saveAndFlush(rappel);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Rappel> findAll() {
        return rappelRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void remove(Long id) {
        rappelRepository.delete(id);
    }

}
