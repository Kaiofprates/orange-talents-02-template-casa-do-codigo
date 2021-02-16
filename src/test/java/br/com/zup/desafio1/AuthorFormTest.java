package br.com.zup.desafio1;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AuthorFormTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void nameEmptyTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("","test@email.com","name test");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].message").isString())
                .andExpect(jsonPath("$[0].message").value("The name field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("name"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void emailEmptyTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","","name test");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].message").isString())
                .andExpect(jsonPath("$[0].message").value("The email field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void emailFormatTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test.com","name test");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].message").isString())
                .andExpect(jsonPath("$[0].message").value("Enter a valid email"))
                .andExpect(jsonPath("$[0].field").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void descriptionEmptyTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test@email.com","");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].message").isString())
                .andExpect(jsonPath("$[0].message").value("The description field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("description"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void descriptionMaxSizeTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test@email.com","tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt1");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].message").isString())
                .andExpect(jsonPath("$[0].message").value("The maximum number of words is 400"))
                .andExpect(jsonPath("$[0].field").value("description"))
                .andDo(MockMvcResultHandlers.print());
    }




}
