package com.voting.votingApplication.repository;

import com.voting.votingApplication.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoterRepository extends JpaRepository<Voter,Long> {
    boolean existsByEmail(String email);
}
