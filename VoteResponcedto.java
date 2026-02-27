package com.voting.votingApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponcedto {
    private String message;
    private boolean success;
    private long voterId;
    private long candidateId;
}
