package com.voting.votingApplication.service;

import com.voting.votingApplication.entity.Candidate;
import com.voting.votingApplication.entity.ElectionResult;
import com.voting.votingApplication.exception.ResourceNotFoundException;
import com.voting.votingApplication.repository.CandidateRepository;
import com.voting.votingApplication.repository.ElectionResultRepository;
import com.voting.votingApplication.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionResultService {

    private CandidateRepository candidateRepository;
    private ElectionResultRepository electionResultRepository;
    private VoteRepository voteRepository;

    @Autowired
    public ElectionResultService(CandidateRepository candidateRepository, ElectionResultRepository electionResultRepository, VoteRepository voteRepository) {
        this.candidateRepository = candidateRepository;
        this.electionResultRepository = electionResultRepository;
        this.voteRepository = voteRepository;
    }
    public ElectionResult declareElectionResult(String electionName){
        Optional<ElectionResult> existingResult=this.electionResultRepository.findByElectionName(electionName);
        if (existingResult.isPresent()){
            return existingResult.get();
        }
        if (voteRepository.count()==0){
            throw new IllegalStateException("Can not declared the result as no vote have been casted");
        }
       List<Candidate> allCandidate=candidateRepository.findAllByOrderByVoteCountDesc();
        if (allCandidate.isEmpty()){
            throw new ResourceNotFoundException("No candidate available");
        }
        Candidate winner=allCandidate.get(0);
        int totalVotes=0;
        for (Candidate c:allCandidate){
            totalVotes+=c.getVoteCount();
        }
        ElectionResult result=new ElectionResult();
        result.setElectionName(electionName);
        result.setWinner(winner);
        result.setTotalVotes(totalVotes);
        return electionResultRepository.save(result);
    }
    public List<ElectionResult>getAllResult(){
        return electionResultRepository.findAll();
    }
}
