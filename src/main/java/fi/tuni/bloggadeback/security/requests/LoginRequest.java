package fi.tuni.bloggadeback.security.requests;

import javax.validation.constraints.NotBlank;

/**
 * This class contains required information for login
 */
public class LoginRequest {

    /**
     * User's username canno't be blank
     */
    @NotBlank
    private String userName;

    /**
     * User's password cannot't be blank
     */
    @NotBlank
    private String password;

    /**
     * Returns username
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets value for username
     *
     * @param userName username value
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets value for password
     *
     * @param password password value
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
