package com.voting.votingApplication.controler;

import com.voting.votingApplication.DTO.VoteRequestDTO;
import com.voting.votingApplication.DTO.VoteResponcedto;
import com.voting.votingApplication.entity.Vote;
import com.voting.votingApplication.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VoteController {
    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/cast")
    public ResponseEntity<VoteResponcedto> castVote(@RequestBody @Valid VoteRequestDTO voteRequestDTO){
        Vote vote=voteService.castVote(voteRequestDTO.getVoterId(),voteRequestDTO.getCandidateId());
        VoteResponcedto voteResponcedto=new VoteResponcedto("Vote casted successfully",true,vote.getVoterId(),vote.getCandidateId());
        return new ResponseEntity<>(voteResponcedto, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Vote>>getAllVotes(){
        List<Vote>voteList=voteService.getAllVotes();
        return new ResponseEntity<>(voteList,HttpStatus.OK);
    }
}
