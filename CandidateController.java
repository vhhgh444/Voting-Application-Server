package com.voting.votingApplication.controler;


import com.voting.votingApplication.entity.Candidate;
import com.voting.votingApplication.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {
    private CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate){
        Candidate savedCandidate=candidateService.addCandidate(candidate);
        return new ResponseEntity<Candidate>(savedCandidate, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates(@RequestBody @Valid Candidate candidate){
        List<Candidate> candidateList=this.candidateService.getAllCandidates();
        return new ResponseEntity<List<Candidate>>(candidateList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id){
        Candidate candidate=this.candidateService.getCandidateById(id);
        return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate>updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate){
        Candidate updatedCandidate=candidateService.updateCandidate(id,candidate);
        return new ResponseEntity<>(updatedCandidate,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteCandidate(@PathVariable Long id){
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate with id "+id+" deleted Successfully",HttpStatus.OK);
    }

}
