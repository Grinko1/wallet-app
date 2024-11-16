package ru.testtask.walletapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty("id")
    private UUID id;
    @Column
    private double balance;
}
