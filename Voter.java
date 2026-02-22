package com.voting.votingApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid mail format")
    private String email;

    private boolean hasVoted=false;

    @OneToOne(mappedBy = "voter",cascade =CascadeType.ALL)
    @JsonIgnore
    private Vote vote;
}
