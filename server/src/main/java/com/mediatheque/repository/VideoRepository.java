package com.mediatheque.repository;

import com.mediatheque.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author Ghiles FEGHOUL
 * @Date 27/01/2018
 * @Licence MIT
 */
public interface VideoRepository extends JpaRepository<Video, Long> {
}
