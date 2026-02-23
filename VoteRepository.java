package com.voting.votingApplication.repository;

import com.voting.votingApplication.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {
}
