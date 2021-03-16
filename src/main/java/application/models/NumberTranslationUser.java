package application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "number_translation_user")
public class NumberTranslationUser implements UserDetails
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private boolean active;

    public NumberTranslationUser(){
        this.active = true;
    }

    public NumberTranslationUser(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.active = true;
    }

    @OneToMany(mappedBy = "numberTranslationUser", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserHistory> userHistories;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "number_translation_user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setUserHistories(Set<UserHistory> userHistories)
    {
        this.userHistories = userHistories;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive();
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public Set<UserHistory> getUserHistories() {
        return userHistories;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
