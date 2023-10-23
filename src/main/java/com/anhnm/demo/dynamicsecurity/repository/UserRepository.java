package com.anhnm.demo.dynamicsecurity.repository;

import com.anhnm.demo.dynamicsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
