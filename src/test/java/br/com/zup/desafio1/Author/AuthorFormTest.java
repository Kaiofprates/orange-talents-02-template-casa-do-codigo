package br.com.zup.desafio1.Author;

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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthorFormTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager em;

    @DisplayName("Deveria lidar com nome vazio")
    @Test
    public void nameEmptyTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("","test@email.com","name test");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("The name field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("name"))
                .andDo(MockMvcResultHandlers.print());
    }
    @DisplayName(" Deveria lidar com email vazio")
    @Test
    public void emailEmptyTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","","name test");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("The email field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com email mal formatado")
    @Test
    public void emailFormatTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test.com","name test");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("Enter a valid email"))
                .andExpect(jsonPath("$[0].field").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com descrição vazia")
    @Test
    public void descriptionEmptyTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test@email.com","");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("The description field cannot be empty"))
                .andExpect(jsonPath("$[0].field").value("description"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com descrição extrapolando o número maximo de caracteres")
    @Test
    public void descriptionMaxSizeTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test@email.com","tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt1");
        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("The maximum number of words is 400"))
                .andExpect(jsonPath("$[0].field").value("description"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com email duplicado")
    @Transactional
    @Test
    public void duplicateEmailTest() throws  Exception{
        AuthorRequestTest data  = new AuthorRequestTest("John Doe","test@email.com","new author");

        Author author = new Author(data.getName(), data.getEmail(), data.getDescription());
        em.persist(author);

        mockMvc.perform(MockMvcRequestBuilders.post("/author")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(data))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("duplicate values"))
                .andExpect(jsonPath("$[0].field").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }


}
