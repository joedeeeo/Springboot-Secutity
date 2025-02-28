package com.sbs.springbootsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.springbootsecurity.domain.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

	Optional<Student> findByUsername(String username);

}
