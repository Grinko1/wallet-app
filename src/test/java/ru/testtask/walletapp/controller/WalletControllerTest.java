package ru.testtask.walletapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.testtask.walletapp.dto.RequestDto;
import ru.testtask.walletapp.exceptions.NotFoundException;
import ru.testtask.walletapp.model.OperationType;
import ru.testtask.walletapp.model.Wallet;
import ru.testtask.walletapp.service.WalletServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalletController.class)
public class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletServiceImpl walletService;

    @Test
    public void testGetAllWallets() throws Exception {
        UUID walletId1 = UUID.randomUUID();
        UUID walletId2 = UUID.randomUUID();
        Wallet wallet1 = new Wallet(walletId1, 1000.0);
        Wallet wallet2 = new Wallet(walletId2, 2000.0);
        List<Wallet> walletList = Arrays.asList(wallet1, wallet2);

        when(walletService.findAllWallets()).thenReturn(walletList);
K
        mockMvc.perform(get("/api/v1/wallets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(walletId1.toString()))
                .andExpect(jsonPath("$[1].id").value(walletId2.toString()));
    }
    @Test
    public void testUpdateBalance() throws Exception {
        RequestDto request = new RequestDto();
        request.setValletId(UUID.randomUUID());
        request.setOperationType(OperationType.DEPOSIT);
        request.setAmount(1000L);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"valletId\":\"" + request.getValletId() + "\",\"operationType\":\"DEPOSIT\",\"amount\":1000}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWalletBalance() throws Exception {
        UUID walletId = UUID.randomUUID();
        when(walletService.findWalletById(walletId)).thenReturn(1000.0);

        mockMvc.perform(get("/api/v1/wallets/" + walletId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWalletBalanceNotFound() throws Exception {
        UUID walletId = UUID.randomUUID();
        doThrow(new NotFoundException("Wallet", "walletId", walletId)).when(walletService).findWalletById(walletId);

        mockMvc.perform(get("/api/v1/wallets/" + walletId))
                .andExpect(status().isNotFound());
    }
}