package fi.tuni.bloggadeback.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * This class contains all required information for user
 */
@Entity
public class ApplicationUser {

    /**
     * User's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * User's username
     */
    private String username;

    /**
     * User's password
     */
    @JsonIgnore
    private String password;

    /**
     * User's role
     */
    private String role;

    /**
     * Constructor for ApplicationUser
     */
    public ApplicationUser() {
    }

    /**
     * Constructor for ApplicationUser
     *
     * @param username User's username
     * @param password User's password
     * @param role User's role
     */
    public ApplicationUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns users id
     *
     * @return User's id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets value for id
     *
     * @param id User's id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Returns user's username
     *
     * @return User's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets value for username
     *
     * @param username User's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns user's password
     *
     * @return User's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets value for password
     *
     * @param password User's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set value for role
     *
     * @param role User's role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns user's role
     *
     * @return User's role
     */
    public String getRole() {
        return role;
    }
}
