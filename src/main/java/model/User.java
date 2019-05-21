package model;

import utils.HashUtil;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)  //означает что когда будем удалять юзера он будет поидее удалит роль
    @JoinColumn(name = "role_id")         //колонка которую создаст хиб
    private Role role;

    @Column(name = "salt")
    private String salt;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role")
//    private Role role;

//    public User(Long id, String name, String password) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//        this.salt = HashUtil.getRandomSalt();
//    }

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.salt = HashUtil.getRandomSalt();
    }

    public User(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = HashUtil.getRandomSalt();
    }

    public User(String name, String password, String email, Role role, String salt) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role) &&
                Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, role, salt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", salt='" + salt + '\'' +
                '}';
    }
}
