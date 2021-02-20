package br.com.zup.desafio1.Book;

import br.com.zup.desafio1.Author.Author;
import br.com.zup.desafio1.Category.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ImageBanner;
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
public class BookTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;

    // cenário de testes

    //cadastro de categoria e autor de testes

    Author testAuthor = new Author("John Doe","john@email.com","um autor de teste");
    Category categoriaDeTeste = new Category("tester");

    String bookRequest = "" +
            "{\n" +
            "    \"title\" : \"Clube da luta\",\n" +
            "    \"resume\" : \"um cara que achava que era dois\",\n" +
            "    \"sumary\" : \"O filme foi melhor\",\n" +
            "    \"price\" : 49.90,\n" +
            "    \"pages\" : 380,\n" +
            "    \"isbn\" :  \"ISBN-31231223\",\n" +
            "    \"publication\" : \"2021-11-13\",\n" +
            "    \"categoryId\" : 1,\n" +
            "    \"authorId\" : 1\n" +
            "}";

    public BookTest() throws JSONException {
    }

    @DisplayName(value = "Espera cadastrar um livro com sucesso")
    @Transactional
    @Test
    public void LivroCadastradoComSucesso() throws Exception{
        manager.persist(testAuthor);
        manager.persist(categoriaDeTeste);
        Assert.assertNotNull("Autor não criado ",testAuthor);

        mockMvc.perform(MockMvcRequestBuilders.post("/book")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookRequest)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").isString())
                .andExpect(jsonPath("$.title").value("Clube da luta"))
                .andDo(MockMvcResultHandlers.print());
    }

}
