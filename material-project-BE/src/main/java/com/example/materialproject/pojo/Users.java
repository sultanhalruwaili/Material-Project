package com.example.materialproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;

    @Length(max = 50 ,min = 2)
    @Column(unique = true,nullable = false)
   private String fullname;
    @Length(max = 15 ,min = 2)
    @Column(unique = true,nullable = false)
    @NotEmpty(message = "Username is required")
   private String username;
    @Length(max = 150 ,min = 4)
    @Column(nullable = false ,unique = true )
   private String email;
    @NotEmpty(message = "Password is required")
   private String password;
    @NotEmpty(message = "Role is required")
    private String role;
    private String status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
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
        return true;
    }
}
