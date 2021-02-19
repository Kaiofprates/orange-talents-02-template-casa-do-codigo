package br.com.zup.desafio1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

public class CategoryFormTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Deveria lidar com nome vazio")
    @Test
    public void nameEmptyTest() throws  Exception{

        CategoryRequestTest data = new CategoryRequestTest("");

        mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("The name field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("name"))
                .andDo(MockMvcResultHandlers.print());
    }

}
