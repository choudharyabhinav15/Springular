package com.mediatheque.repository;

import com.mediatheque.model.Mediatheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediathequeRepository extends JpaRepository<Mediatheque, Long> {
}
