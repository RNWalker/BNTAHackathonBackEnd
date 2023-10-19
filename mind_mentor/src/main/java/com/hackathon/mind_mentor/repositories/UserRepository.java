package com.hackathon.mind_mentor.repositories;

import com.hackathon.mind_mentor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
