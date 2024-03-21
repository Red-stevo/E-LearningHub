package com.redstevo.code.Tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redstevo.code.Models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Component
@AllArgsConstructor
@NoArgsConstructor
public class AuthTable implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @Size(max = 50, min = 2, message = "Username Must Be Between 2 - 50 characters.")
    @NotNull
    private String username;

    @Size(min = 8, max = 20, message = "The Password Must be Between 8 - 20 alphanumerics and special symbols")
    @NotNull
    private String password;

    @JsonIgnore
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    @NotNull
    private Boolean isEnabled;

    @OneToOne(mappedBy = "authTable")
    private UserProfile userProfile;

    @PrePersist
    private void setDefault(){
        this.isEnabled = true;
        this.role = Role.user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
