package net.java.togojug.service;

import net.java.togojug.domain.User;
import net.java.togojug.repository.UserRepository;

/**
 * Implementation of {@link UserService}
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class UserServiceImpl implements UserService {

    // To be injected
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws LoginException {
        
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UnknownUserException(username);
        }
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException(username);
        }
        
        return user;
    }

    public void register(User user) {
        userRepository.create(user);
    }
}
