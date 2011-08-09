package net.java.togojug.service;

import net.java.togojug.domain.User;

/**
 * Business interface for {@link User} management
 * @author Sryl <cyril.lacote@gmail.com>
 */
public interface UserService {
    
    /**
     * Log-in method.
     * If given credentials are valid (username/password match), returns logged in user.
     * If unknown user, throws LoginException.
     * @param username Username
     * @param password Password
     * @return User logged in.
     * @throws InvalidCredentialException if invalid credentials
     * @throws UnknownUser if user does not exists
     */
    User login( String username, String password) throws LoginException;


    /**
     * 
     * @param user 
     */
    void register( User user);
}
