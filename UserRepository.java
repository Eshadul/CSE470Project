package com.programming.techie.Blogers.Point.repository;

import com.programming.techie.Blogers.Point.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUserName(String username);
}
