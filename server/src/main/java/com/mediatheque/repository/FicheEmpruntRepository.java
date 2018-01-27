package com.mediatheque.repository;

import com.mediatheque.model.FicheEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheEmpruntRepository extends JpaRepository<FicheEmprunt, Long> {
}
