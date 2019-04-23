package fi.tuni.bloggadeback.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is required by spring boot security.
 * This class contains required information for user
 */
public class UserPrincipalImpl implements UserDetails {

    /**
     * Application user instance
     */
    private ApplicationUser user;

    /**
     * Constructor for UserPrincipalImpl
     * Initializes ApplicationUser
     *
     * @param user ApplicationUser
     */
    public UserPrincipalImpl(ApplicationUser user) {
        this.user = user;
    }

    /**
     * Puts users roles to a collection
     *
     * @return Collection of Authorities
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    /**
     * Returns user's id
     *
     * @return User's id
     */
    @JsonIgnore
    public Long getId() {
        return user.getId();
    }

    /**
     * Returns user's password
     *
     * @return User's password
     */
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns user's username
     *
     * @return User's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Returns boolean which tells if account is expired or not
     *
     * @return boolean which tells if account is expired or not
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns boolean which tells if account is locked or not
     *
     * @return boolean which tells if account is locked or not
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns boolean which tells if credentials is expired or not
     *
     * @return boolean which tells if credentials is expired or not
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns boolean which tells if account is enabled or not
     *
     * @return boolean which tells if account is enabled or not
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns ApplicationUser
     *
     * @return ApplicationUser
     */
    public ApplicationUser getUser() {
        return user;
    }
}
