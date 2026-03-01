package com.voting.votingApplication.service;

import com.voting.votingApplication.entity.Candidate;
import com.voting.votingApplication.entity.Vote;
import com.voting.votingApplication.entity.Voter;
import com.voting.votingApplication.exception.ResourceNotFoundException;
import com.voting.votingApplication.exception.VoteNotAllowedException;
import com.voting.votingApplication.repository.CandidateRepository;
import com.voting.votingApplication.repository.VoteRepository;
import com.voting.votingApplication.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    private VoteRepository voteRepository;
    private CandidateRepository candidateRepository;
    private VoterRepository voterRepository;

    public VoteService(VoteRepository voteRepository, CandidateRepository candidateRepository, VoterRepository voterRepository) {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }

    @Transactional
    public Vote castVote(Long voterId,Long candidateId){
        if (!voterRepository.existsById(voterId)){
            throw new ResourceNotFoundException("Voter not found with ID: "+voterId);
        }
        if (!candidateRepository.existsById(candidateId)){
            throw new ResourceNotFoundException("Candidate not found with ID: "+candidateId);
        }
        Voter voter=voterRepository.findById(voterId).get();
        if (voter.isHasVoted()){
            throw new VoteNotAllowedException("Voter ID: "+voterId+" has already exist");
        }
        Candidate candidate=candidateRepository.findById(candidateId).get();
        Vote vote=new Vote();
        vote.setVoter(voter);
        vote.setCandidate(candidate);
        voter.setVote(vote);
//        voteRepository.save(vote);

        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);

        voter.setHasVoted(true);
        System.out.println(voter.getVote());
        voterRepository.save(voter);
        return vote;
    }
    public List<Vote>getAllVotes(){
        return voteRepository.findAll();
    }
}
