package com.mediatheque.service;

import com.mediatheque.model.Mediatheque;

import java.util.List;

public interface MediathequeService {

    Mediatheque find(Long id);

    Mediatheque save(Mediatheque mediatheque);

    Mediatheque update(Mediatheque mediatheque);

    List<Mediatheque> findAll();

    void remove(Long id);

}
