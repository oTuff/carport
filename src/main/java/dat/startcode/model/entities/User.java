package dat.startcode.model.entities;

import java.util.List;
import java.util.Objects;

public class User {
    private String email;
    private String fullName;
    private String password;
    private int balance;
    private String address;
    private int zipNr;
    private String role;

    private List<User> userList;

    public User(String email, String fullName, String password, int balance, String address, int zipNr, String role) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.balance = balance;
        this.address = address;
        this.zipNr = zipNr;
        this.role = role;
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipNr() {
        return zipNr;
    }

    public void setZipNr(int zipNr) {
        this.zipNr = zipNr;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getBalance() == user.getBalance() && getZipNr() == user.getZipNr() && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getFullName(), user.getFullName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getFullName(), getPassword(), getBalance(), getAddress(), getZipNr(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", address='" + address + '\'' +
                ", zipNr=" + zipNr +
                ", role='" + role + '\'' +
                '}';
    }
}