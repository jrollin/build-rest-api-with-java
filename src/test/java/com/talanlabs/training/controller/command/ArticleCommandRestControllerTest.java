package com.talanlabs.training.controller.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ArticleCommandRestController.class)
@WithMockUser(roles = {"WRITER"})
public class ArticleCommandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ArticleCommandUseCase articleCommandUseCase;

    @Test
    @WithAnonymousUser
    @DisplayName("Anonymous user cannot submit article")
    public void anonymousNotAllowedToSubmit() throws Exception {
        //when
        //then
        this.mockMvc.perform(
                post("/api/articles")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    @DisplayName("With no data, should return header status code 400 and errors message for each fiels")
    public void submitArticleShouldReturnErrorHeaderWithNoData() throws Exception {
        //when
        //then
        this.mockMvc.perform(
                post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.author").value("Author cannot be empty"))
                .andExpect(jsonPath("$.title").value("Title cannot be empty"));
    }


    @Test
    @DisplayName("With invalid author field, should return header status code 400 and author error message")
    public void submitArticleShouldReturnErrorsWithInvalidAuthor() throws Exception {
        // given
        Map<String, String> formData = new HashMap<>();
        formData.put("title", "test");

        ObjectMapper mapper = new ObjectMapper();
        String dataJson = mapper.writeValueAsString(formData);
        //then
        this.mockMvc.perform(
                post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.author").value("Author cannot be empty"));
    }


    @Test
    @DisplayName("With invalid title field, should return header status code 400 and title error message")
    public void submitArticleShouldReturnErrorsWithInvalidTitle() throws Exception {
        // given
        Map<String, String> formData = new HashMap<>();
        formData.put("author", "test");

        ObjectMapper mapper = new ObjectMapper();
        String dataJson = mapper.writeValueAsString(formData);
        //then
        this.mockMvc.perform(
                post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Title cannot be empty"));
    }


    @Test
    @DisplayName("With valid data, should return header status code 201 and no response data")
    public void submitArticleShouldReturnHeaderCreatedIfSuccess() throws Exception {
        // given
        Map<String, String> formData = new HashMap<>();
        formData.put("title", "title test");
        formData.put("author", "author test");

        ObjectMapper mapper = new ObjectMapper();
        String dataJson = mapper.writeValueAsString(formData);
        // when
        this.mockMvc.perform(
                post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dataJson)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").doesNotExist());

        //verify
        verify(articleCommandUseCase).submitArticle(new SubmitArticleCommand("title test", "author test"));
    }


    @Test
    @DisplayName("A journalist cannot pusblish article")
    public void publishArticleAsJournalistIsForbidden() throws Exception {
        //when
        //then
        this.mockMvc.perform(
                put(String.format("/api/articles/%s/publish/%s", 4L, "2020-10-20"))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    @DisplayName("As a redactor, i cannot publish article without valid date")
    @WithMockUser(roles = "PUBLISHER")
    public void publishArticleWithInvalidDate() throws Exception {
        //when
        //then
        this.mockMvc.perform(
                put(String.format("/api/articles/%s/publish/%s", 4L, "invalid"))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    @DisplayName("As a redactor, i cann publish article with valid date")
    @WithMockUser(roles = "PUBLISHER")
    public void publishArticleShouldReturnAccepted() throws Exception {
        //when
        String dateSent = "2020-10-20";
        //then
        this.mockMvc.perform(
                put(String.format("/api/articles/%s/publish/%s", 4L, dateSent))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$").doesNotExist());
    }


}
