package com.mediatheque.repository;

import com.mediatheque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by Ghiles FEGHOUL on 28/12/2017
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
    User findByEmail( String email );
}

