//package model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Objects;
//
//@Entity
//@Table(name = "user")
//public class User2 {
//
//    @Id
//    @Column(name = "id")
//    protected Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "role")
//    private String role;
//
//    public User2() {
//    }
//
//    public User2(Long id, String name, String password, String role) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//        this.role = role;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User2 user2 = (User2) o;
//        return Objects.equals(id, user2.id) &&
//                Objects.equals(name, user2.name) &&
//                Objects.equals(password, user2.password) &&
//                Objects.equals(role, user2.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, password, role);
//    }
//
//    @Override
//    public String toString() {
//        return "User2{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }
//}
