package fi.tuni.bloggadeback.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class handles loading users from repository
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * ApplicationUserRepository instance
     */
    @Autowired
    ApplicationUserRepository userRepository;

    /**
     * Loads user from repository by username
     *
     * @param username User to be found
     * @return founded user
     * @throws UsernameNotFoundException if user doesn't exist
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipalImpl(user);
    }

    /**
     * Loads user from repository by id
     *
     * @param id User's id
     * @return founded user
     * @throws UsernameNotFoundException if user doesn't exist
     */
    @Transactional
    public UserDetails loadByUserId(long id) {

        ApplicationUser user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found, id: " + id));
        return new UserPrincipalImpl(user);

    }

}
