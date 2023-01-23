package ru.kata.spring.boot_security.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "username")
        private String username;

        @Column(name = "lastName")
        private String lastName;

        @Column(name = "email", unique = true)
        private String email;

        @Column(name = "password")
        private String password;


        @ManyToMany(fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
        @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",
                referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(
                name = "roles_id", referencedColumnName = "id"))

        private Set<Role> roles;

        public User(){
        }


        public User(String username, String lastName, String email, String password, Set<Role> roles) {
            this.username = username;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.roles = roles;
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public void setUsername(String username) {
            this.username = username;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

//        public String getRolesToString() {
//        return roles.toString().replaceAll("^\\[|\\]$", "");
//    }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
}
