package br.com.zup.desafio1.Client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
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
public class ClientTest {

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    private EntityManager manager;


    @DisplayName("Deveria lidar com um país não cadastrado no banco de dados")
    @Test
    public void countryNotFoundTest() throws  Exception{

        String requestTest = "{\n" +
                "    \"email\" : \"\",\n" +
                "    \"name\" : \"tayler\",\n" +
                "    \"surname\" : \"Durden\",\n" +
                "    \"document\" : \"81.196.071/0001-03\", \n" +
                "    \"address\" : \"Paper Street\",\n" +
                "    \"number\" : \"19808\",\n" +
                "    \"complement\" : \"Odeio Marla Singer\",\n" +
                "    \"city\" : \"Bradford\",\n" +
                "    \"country\" : \"Japão\", \n" +
                "    \"state\" : \"Honk-Kong\",\n" +
                "    \"phone\" : \"555-0153\",\n" +
                "    \"cep\" : \"39401138\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestTest)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$[0].status").value(400))
                .andExpect(jsonPath("$[0].error").isString())
                .andExpect(jsonPath("$[0].error").value("Id not found"))
                .andExpect(jsonPath("$[0].field").value("country"))
                .andDo(MockMvcResultHandlers.print());
    }

}
