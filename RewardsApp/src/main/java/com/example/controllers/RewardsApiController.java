package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.RewardsRequest;
import com.example.model.RewardsResponse;
import com.example.service.RetailRewardService;

@RequestMapping(value = "/api")
@RestController
public class RewardsApiController {

	@Autowired
	private RetailRewardService rewardPointsService;

	@PostMapping(value = "/rewards")
	public ResponseEntity<List<RewardsResponse>> getAllRewardsEarned(@RequestBody RewardsRequest request) {

		List<RewardsResponse> rewards = rewardPointsService.getRewardPoints(request);

		return new ResponseEntity<List<RewardsResponse>>(rewards, HttpStatus.OK);
	}

}
