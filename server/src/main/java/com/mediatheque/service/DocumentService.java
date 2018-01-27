package com.mediatheque.service;

import com.mediatheque.model.Document;
import com.mediatheque.model.Localisation;

import java.util.List;


public interface DocumentService {

    Document save(Document document);

    Document update(Document document);

    Document find(Long id);

    List<Document> findAll();

    void remove(Long id);

    List<Document> findDocumentsByLocalization(Localisation localisation);
}
