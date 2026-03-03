package com.voting.votingApplication.controler;

import com.voting.votingApplication.DTO.ElectionResultRequestDTO;
import com.voting.votingApplication.DTO.ElectionResultResponseDTO;
import com.voting.votingApplication.entity.ElectionResult;
import com.voting.votingApplication.service.ElectionResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election_result")
@CrossOrigin
public class ElectionResultController {
    private ElectionResultService electionResultService;

    @Autowired
    public ElectionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }
    @PostMapping("/declared")
    public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO){
        ElectionResult result=electionResultService.declareElectionResult(electionResultRequestDTO.getElectionName());
        ElectionResultResponseDTO responseDTO=new ElectionResultResponseDTO();
        responseDTO.setElectionName(result.getElectionName());
        responseDTO.setTotalVotes(result.getTotalVotes());
        responseDTO.setWinnerId(result.getWinnerId());
        responseDTO.setWinnerVotes(result.getWinner().getVoteCount());
        return  ResponseEntity.ok(responseDTO);
    }
    @GetMapping
    public ResponseEntity<List<ElectionResult>> getAllResult(){
        List<ElectionResult>results=electionResultService.getAllResult();
        return ResponseEntity.ok(results);
    }

}
