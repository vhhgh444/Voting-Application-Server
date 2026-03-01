package com.voting.votingApplication.service;

import com.voting.votingApplication.entity.Candidate;
import com.voting.votingApplication.entity.Vote;
import com.voting.votingApplication.entity.Voter;
import com.voting.votingApplication.exception.DuplicateResourceException;
import com.voting.votingApplication.exception.ResourceNotFoundException;
import com.voting.votingApplication.repository.CandidateRepository;
import com.voting.votingApplication.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {
    private VoterRepository voterRepository;
    private CandidateRepository candidateRepository;

    @Autowired
    public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }
    public Voter registerVoter(Voter voter){
        if(voterRepository.existsByEmail(voter.getEmail())){
            throw new DuplicateResourceException("Voter with email id:- "+voter.getEmail()+"already exist");
        }
        return voterRepository.save(voter);
    }
    public List<Voter>getAllVoters(){
        return voterRepository.findAll();
    }
    public Voter getVoterById(Long id){
        Voter voter=voterRepository.findById(id).orElse(null);
        if (voter==null){
            throw new ResourceNotFoundException("Voter with id: "+id+" not found");
        }
        return voter;
    }
    public Voter updateVoter(Long id,Voter updatedVoter){
        Voter voter=voterRepository.findById(id).orElse(null);
        if(voter==null){
            throw new ResourceNotFoundException("Voter with id: "+id+" not found");
        }
        if (updatedVoter.getName()!=null) {
            voter.setName(updatedVoter.getName());
        }
        if (updatedVoter.getEmail()!=null) {
            voter.setEmail(updatedVoter.getEmail());
        }
        return voterRepository.save(voter);
    }
    @Transactional
    public void deleteVoter(Long id){
        Voter voter=voterRepository.findById(id).orElse(null);
        if(voter==null){
            throw new ResourceNotFoundException("Can't delete voter with id : "+id+" as that id is not exist");
        }
        Vote vote=voter.getVote();
        if (vote!=null){
            Candidate candidate=vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount()-1);
            candidateRepository.save(candidate);
        }
        voterRepository.delete(voter);
    }

}
