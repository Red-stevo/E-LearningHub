package com.redstevo.code.Tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redstevo.code.Models.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AuthTable implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @NotNull
    private String username;

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

    @OneToMany(mappedBy = "authTable")
    private List<TokensTable> tokensTableList;

    //setting default values.
    {
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
