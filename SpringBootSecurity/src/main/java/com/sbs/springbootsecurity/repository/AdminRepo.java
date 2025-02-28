package com.sbs.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.springbootsecurity.domain.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long>{

}
