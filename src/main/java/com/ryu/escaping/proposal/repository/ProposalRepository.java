package com.ryu.escaping.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.proposal.domain.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {

}
