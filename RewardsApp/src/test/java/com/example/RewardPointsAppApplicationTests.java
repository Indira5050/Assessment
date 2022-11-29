package com.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.model.RewardsRequest;
import com.example.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
class RewardPointsAppApplicationTests extends AbstractTest {

	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	@Test
	public void rewardsControllerTest() throws Exception {
 
		RewardsRequest request = new RewardsRequest();
		request.setTimePeriod(3);
		List<Transaction> list = new ArrayList<>();

		Transaction transaction = new Transaction();
		transaction.setAmount(120);
		transaction.setTransactionDate(LocalDate.parse("2022-09-02"));
		transaction.setCustomerid(2222);
		list.add(transaction);

		transaction = new Transaction();
		transaction.setAmount(150);
		transaction.setTransactionDate(LocalDate.parse("2022-11-02"));
		transaction.setCustomerid(1111);
		list.add(transaction);

		transaction = new Transaction();
		transaction.setAmount(150);
		transaction.setTransactionDate(LocalDate.parse("2022-10-02"));
		transaction.setCustomerid(2222);
		list.add(transaction);

		request.setTransactions(list);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		String reqStr = mapper.writeValueAsString(request);

		String uri = "/api/rewards";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.content(reqStr).contentType(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		List result = super.mapFromJson(content, List.class);
		System.out.println(result);
		assertNotNull(result);
		// assertTrue(result.get(0).equals("Divided 1 by 3"));
		// assertTrue(result.get(1).equals("Divided 1 by 5"));

	}
}
