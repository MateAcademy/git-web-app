package model;

import utils.HashUtil;

import java.util.Objects;

public class User {
    protected Long id;
    private String name;
    private String password;
    private String email;
    private Integer role;
    private String salt;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.salt = HashUtil.getRandomSalt();
    }

    public User(String name, String password, String email, Integer role) {
        this(name, password);
        this.role = role;
        this.email = email;
    }

    public User(Long id, String name, String password, Integer role, String salt) {
        this.id = id;
        this.name = name;
        this.password=password;
        this.role=role;
        this.salt = salt;
    }

    public User(Long id, String name,  String password, String email, Integer role, String salt) {
        this(id, name, password, role, salt);
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
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
