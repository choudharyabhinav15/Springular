package com.mediatheque.service.impl;

import com.mediatheque.model.Document;
import com.mediatheque.model.Localisation;
import com.mediatheque.repository.DocumentRepository;
import com.mediatheque.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document save(Document document) {
        return this.documentRepository.save(document);
    }

    @Override
    public Document update(Document document) {
        return this.documentRepository.saveAndFlush(document);
    }

    @Override
    public Document find(Long id) {
        return this.documentRepository.findOne(id);
    }

    @Override
    public List<Document> findAll() {
        return this.documentRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        this.documentRepository.delete(id);
    }

    @Override
    public List<Document> findDocumentsByLocalization(Localisation localisation) {
        return documentRepository.findDocumentsByLocalization(localisation);
    }

}
