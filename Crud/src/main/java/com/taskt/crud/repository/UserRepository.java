package com.taskt.crud.repository;

import com.taskt.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // This interface automatically gives you save(), findById(), findAll(), and deleteById()
}