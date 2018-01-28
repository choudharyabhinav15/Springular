package com.mediatheque.repository;

import com.mediatheque.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface AudioRepository extends JpaRepository<Audio, Long> {

}
