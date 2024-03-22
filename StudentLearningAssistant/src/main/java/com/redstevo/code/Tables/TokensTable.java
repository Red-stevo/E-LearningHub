package com.redstevo.code.Tables;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor
public class TokensTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @NotNull
    private String token;

    @NotNull
    private Boolean isLoggedOut;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "auth_table_fk")
    private AuthTable authTable;

    /*Setting default value*/
    {
        this.isLoggedOut = false;
    }
}
