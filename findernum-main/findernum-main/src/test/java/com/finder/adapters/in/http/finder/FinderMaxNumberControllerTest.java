package com.finder.adapters.in.http.finder;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finder.application.ports.in.FindMaxNumberUseCase;
import com.finder.application.service.OutOfRangeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = FindMaxNumberController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class FinderMaxNumberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FindMaxNumberUseCase findMaxNumber;

    @InjectMocks
    private FindMaxNumberController findMaxNumberController;

    @Test
    void testFindMaxKWithGetRequest() throws Exception {
        int x = 5;
        int y = 2;
        int n = 20;
        int expectedResult = 17;

        // Mocking the service response
        when(findMaxNumber.findMaxNumber(x, y, n)).thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/findMaxK")
                        .param("x", String.valueOf(x))
                        .param("y", String.valueOf(y))
                        .param("n", String.valueOf(n))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(expectedResult));
    }

    @Test
    void testFindMaxKWithPostRequest() throws Exception {
        int x = 5;
        int y = 2;
        int n = 20;
        int expectedResult = 17;
        RequestDto requestDto = new RequestDto(x, y, n);

        // Mocking the service response
        when(findMaxNumber.findMaxNumber(x, y, n)).thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/findMaxK")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("ok"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(expectedResult));
    }

    @Test
    void testFindMaxKWithOutOfRangeParameters() throws Exception {
        // Configuración del test
        int x = 1; // x fuera del rango permitido
        int y = 2;
        int n = 20;
        String expectedErrorMessage = "El valor de x está fuera del rango permitido";

        // Simular la llamada al método find y lanzar IllegalArgumentException
        when(findMaxNumber.findMaxNumber(anyInt(), anyInt(), anyInt()))
                .thenThrow(new OutOfRangeException(expectedErrorMessage));

        // Realizar la solicitud al endpoint y verificar la respuesta
        mockMvc.perform(MockMvcRequestBuilders.get("/api/findMaxK")
                        .param("x", String.valueOf(x))
                        .param("y", String.valueOf(y))
                        .param("n", String.valueOf(n))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Error de rango permitido"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("1021"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value(expectedErrorMessage));
    }
}
