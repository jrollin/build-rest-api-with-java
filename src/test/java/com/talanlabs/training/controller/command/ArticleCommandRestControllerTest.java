package com.talanlabs.training.controller.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ArticleCommandRestController.class)
public class ArticleCommandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ArticleCommandUseCase articleCommandUseCase;

    @Test
    public void shouldReturnErrorHeaderWithNoData() throws Exception {
        //when
        //then
        this.mockMvc.perform(
                post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.author").value("Author cannot be empty"))
                .andExpect(jsonPath("$.title").value("Title cannot be empty"));
    }


    @Test
    public void shouldReturnErrorsWithInvalidAuthor() throws Exception {
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
                .andExpect(jsonPath("$.author").value("Author cannot be empty"));
    }


    @Test
    public void shouldReturnHeaderCreatedIfSuccess() throws Exception {
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
}
