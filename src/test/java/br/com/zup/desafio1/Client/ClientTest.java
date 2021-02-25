package br.com.zup.desafio1.Client;

import br.com.zup.desafio1.Country.Country;
import br.com.zup.desafio1.State.State;
import br.com.zup.desafio1.handler.exceptions.CustonMessageException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
import org.springframework.web.util.NestedServletException;

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

    @Before
    @Transactional
    public static void populaBanco(EntityManager manager){

        Country country = new Country("Japão");
        manager.persist(country);
        System.out.printf("-========== popula =============-");
    }



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
    @DisplayName("Deveria lidar com um estado não cadastrado ou não vinculado a um pais")

    @Test
    public void stateNotFoundTest() throws  Exception{

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

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/client")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestTest)
            );
        }catch (NestedServletException e){
            Assert.fail();
        }
    }


    @Before
    @Transactional
    public static void populaBancoComEstado(EntityManager manager){
        State state = new State("Honk-Kong",1L,"Japão");
        manager.persist(state);
    }

    @DisplayName("Deveria lidar com um email vazio")
    @Transactional
    @Test
    public void emailNotFoundTest() throws  Exception{


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
                .andExpect(jsonPath("$[0].field").value("email"))
                .andDo(MockMvcResultHandlers.print());
    }
}
