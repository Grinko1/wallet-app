package ru.testtask.walletapp.dto;

import lombok.*;
import ru.testtask.walletapp.model.OperationType;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestDto {
    private UUID walletId;
    private OperationType operation;
    private Long amount;

}
