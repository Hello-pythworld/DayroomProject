package com.Dayroom.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dayroom.project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);

}
