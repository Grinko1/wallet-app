package ru.testtask.walletapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.dto.ResponseDto;
import ru.testtask.walletapp.exceptions.InvalidOperationTypeException;
import ru.testtask.walletapp.model.OperationType;
import ru.testtask.walletapp.model.Wallet;
import ru.testtask.walletapp.service.WalletServiceImpl;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WalletServiceImpl service;

    @InjectMocks
    private WalletController controller;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID walletId;
    private RequestDto requestDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        walletId = UUID.randomUUID();
        requestDto = new RequestDto(walletId, OperationType.DEPOSIT, 100L);
    }

    @Test
    public void testGetAllWallets() throws Exception {
        // Arrange
        when(service.findAllWallets()).thenReturn(List.of(new Wallet(), new Wallet()));  // Мокаем результат

        // Act & Assert
        mockMvc.perform(get("/api/v1/wallets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));  // Проверяем, что ответ содержит 2 кошелька
    }

    @Test
    public void testGetWalletBalance() throws Exception {
        // Arrange
        ResponseDto responseDto = new ResponseDto(100.0);  // Пример баланса
        when(service.findWalletById(walletId)).thenReturn(100.0);  // Мокаем результат

        // Act & Assert
        mockMvc.perform(get("/api/v1/wallets/{WALLET_UUID}", walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(100.0));  // Проверяем, что баланс равен 100
    }

    @Test
    public void testUpdateBalance() throws Exception {
        // Arrange
        when(service.updateWalletBalance(any(RequestDto.class))).thenReturn("Баланс обновлён");  // Мокаем результат

        // Act & Assert
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))  // Конвертируем RequestDto в JSON
                .andExpect(status().isOk())
                .andExpect(content().string("Баланс обновлён"));  // Проверяем, что ответ правильный
    }

    @Test
    public void testUpdateBalanceInvalidOperation() throws Exception {
        // Arrange
        RequestDto invalidRequestDto = new RequestDto(walletId, null, 100L);  // Невалидная операция
        when(service.updateWalletBalance(any(RequestDto.class))).thenThrow(new InvalidOperationTypeException("Неверный тип операции"));

        // Act & Assert
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequestDto)))  // Конвертируем RequestDto в JSON
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Неверный тип операции"));  // Проверяем, что ошибка была выброшена с правильным сообщением
    }
}
