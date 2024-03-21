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
public class UserProfile {

    @Id
    private String username;

    private String firstName;

    private String secondName;

    @Column(unique = true)
    private String email;

    private String imageUrl;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "auth_table_fk")
    private AuthTable authTable;

}
