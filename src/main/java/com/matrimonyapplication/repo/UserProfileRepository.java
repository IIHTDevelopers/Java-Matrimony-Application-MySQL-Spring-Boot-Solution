package com.matrimonyapplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matrimonyapplication.entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	List<UserProfile> findByLikesContaining(String likesKeyword);

	List<UserProfile> findAllBySex(String sex);
}
