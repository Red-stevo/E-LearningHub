package com.redstevo.code.Tables;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Entity
@Component
public class RefreshTokenTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "user_fk")
    private AuthTable authTable;
}
