package fi.tuni.bloggadeback.user;

import org.springframework.data.repository.CrudRepository;

/**
 * This is repository for ApplicationUsers
 */
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    /**
     * Searches users from repository by username
     *
     * @param username User to be found
     * @return founded user
     */
    ApplicationUser findByUsername(String username);

    /**
     * Checks if there is user with given username in the repository
     *
     * @param username User to be found
     * @return boolean which tells if it exists or not
     */
    boolean existsByUsername(String username);
}
