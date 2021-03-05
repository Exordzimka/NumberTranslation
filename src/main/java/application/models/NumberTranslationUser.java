package application.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "number_translation_user")
public class NumberTranslationUser
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String login;
    private String password;

    public NumberTranslationUser(){}

    public NumberTranslationUser(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    @OneToMany(mappedBy = "numberTranslationUser", fetch = FetchType.EAGER)
    private Set<UserHistory> userHistories;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "number_translation_user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Set<UserHistory> getUserHistories()
    {
        return userHistories;
    }
}
