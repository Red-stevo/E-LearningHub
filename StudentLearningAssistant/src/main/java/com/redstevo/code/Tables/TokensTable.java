package com.redstevo.code.Tables;

import jakarta.persistence.*;
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

    private String token;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "auth_table_fk")
    private AuthTable authTable;
}
