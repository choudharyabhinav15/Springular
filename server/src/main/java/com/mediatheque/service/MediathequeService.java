package com.mediatheque.service;

import com.mediatheque.model.Mediatheque;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface MediathequeService {

    Mediatheque find(Long id);

    Mediatheque save(Mediatheque mediatheque);

    Mediatheque update(Mediatheque mediatheque);

    List<Mediatheque> findAll();

    void remove(Long id);

}
