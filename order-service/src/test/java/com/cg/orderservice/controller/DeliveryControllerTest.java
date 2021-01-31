/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-31 23:26:39
 * @modify date 2021-01-31 23:26:39
 * @desc [description]
 */
package com.cg.orderservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DeliveryControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  static {
    System.setProperty("spring.profiles.active", "test");
  }

  @Test
  void fetchDeliveryHistorySuccessCode() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/delivery/100001")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk());
  }

  @Test
  void fetchDeliveryHistorySuccessData() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/delivery/100001")
    .contentType(MediaType.APPLICATION_JSON))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.[0].orderId", Matchers.is(100001)));
  }

  @Test
  void fetchDelieryHistoryFail() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/delivery/" + 100999)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError());
  }


}
