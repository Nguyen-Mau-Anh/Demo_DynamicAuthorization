package com.anhnm.demo.dynamicsecurity.repository;

import com.anhnm.demo.dynamicsecurity.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByEndpoint(String endPoint);
}
