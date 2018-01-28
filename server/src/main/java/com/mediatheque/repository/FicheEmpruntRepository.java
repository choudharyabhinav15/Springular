package com.mediatheque.repository;

import com.mediatheque.model.FicheEmprunt;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface FicheEmpruntRepository extends JpaRepository<FicheEmprunt, Long> {
}
