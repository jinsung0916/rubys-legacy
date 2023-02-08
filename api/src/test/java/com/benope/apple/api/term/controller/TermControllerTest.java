package com.benope.apple.api.term.controller;

import com.benope.apple.api.AppleTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("TermControllerTest.sql")
public class TermControllerTest extends AppleTest {

    @Test
    public void 약관을_조회한다() throws Exception {
        // When
        ResultActions resultActions = mockMvc.perform(
                        post("/get.term")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .content(mapper.writeValueAsString(Map.of()))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.rst_content.size()").value(4))
                .andExpect(jsonPath("$.rst_content[0].term_version").value("0.2"))
                .andExpect(jsonPath("$.rst_content[1].term_version").value("0.2"))
                .andExpect(jsonPath("$.rst_content[2].term_version").value("0.2"))
                .andExpect(jsonPath("$.rst_content[3].term_version").value("0.2"));
    }

    @Test
    public void 마케팅_약관을_조회한다() throws Exception {
        // When
        ResultActions resultActions = mockMvc.perform(
                        post("/get.marketing.term")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                                .content(mapper.writeValueAsString(Map.of()))
                )
                .andDo(print());

        // Then
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.rst_content.term_cd.code").value("TERM04"))
                .andExpect(jsonPath("$.rst_content.term_version").value("0.2"));
    }

}
