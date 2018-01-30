package com.mediatheque.service;

import com.mediatheque.model.Rappel;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface RappelService {

    Rappel find(Long id);

    Rappel save(Rappel rappel);

    Rappel update(Rappel rappel);

    List<Rappel> findAll();

    void remove(Long id);
}
