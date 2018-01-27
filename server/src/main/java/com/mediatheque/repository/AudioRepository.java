package com.mediatheque.repository;

import com.mediatheque.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Long> {

}
