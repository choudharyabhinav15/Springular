package com.mediatheque.service;

import com.mediatheque.model.Rappel;

import java.util.List;

public interface RappelService {

    Rappel find(Long id);

    Rappel save(Rappel rappel);

    Rappel update(Rappel rappel);

    List<Rappel> findAll();

    void remove(Long id);

}
