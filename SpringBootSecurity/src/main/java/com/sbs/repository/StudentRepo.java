package com.sbs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.domain.StudentDetails;

@Repository
public interface StudentRepo extends JpaRepository<StudentDetails, Long>{

	Optional<StudentDetails> findByUserName(String username);

}
