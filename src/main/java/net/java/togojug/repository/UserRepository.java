package net.java.togojug.repository;

import net.java.togojug.domain.User;

/**
 * Repository interface for {@link User} data access.
 * @author Sryl <cyril.lacote@gmail.com>
 */
public interface UserRepository {
    
    /**
     * Find existing user by username
     * @param username Searched username
     * @return Existing user, null if not exists.
     */
    User findByUsername(String username);
    
    /**
     * Insert given user in database
     * @param user user to create
     */
    void create(User user);
}
