package com.mediatheque.service;

import com.mediatheque.model.FicheEmprunt;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface FicheEmpruntService {

    FicheEmprunt find(Long id);

    FicheEmprunt save(FicheEmprunt ficheEmprunt);

    FicheEmprunt update(FicheEmprunt ficheEmprunt);

    List<FicheEmprunt> findAll();

    void remove(Long id);

}
