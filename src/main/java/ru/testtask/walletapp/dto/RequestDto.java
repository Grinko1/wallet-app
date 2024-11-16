package ru.testtask.walletapp.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import ru.testtask.walletapp.model.OperationType;
import ru.testtask.walletapp.validation.ValidOperationType;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDto {
    @NotNull(message = "Wallet ID не может быть пустым")
    private UUID valletId;
    @NotNull(message = "Операция не может быть пустой")
    private OperationType operationType;
    @NotNull(message = "Сумма не может быть пустой")
    @Positive(message = "Сумма должна быть больше 0")
    private Long amount;
}