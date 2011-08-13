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

    /**
     * Méthode de login.
     * Si le mot de passe donné est valide, retourne l'utilisateur loggé. Sinon, lève l'exception {@link InvalidCredentialsException}
     * Si l'utilisateur est inconnu, lève l'exception {@link UnknownUserException}
     * @param username Nom d'utilisateur
     * @param password Mot de passe
     * @return Utilisateur loggé.
     * @throws InvalidCredentialException si le mot de passe est erroné
     * @throws UnknownUser si l'utilisateur est inconnu
     */
    public User login(String username, String password) throws LoginException {
        
        // Recherche de l'utilisateur en BDD
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            // L'utilisateur n'existe pas
            throw new UnknownUserException(username);
        }
        // L'utilisateur existe
        if (!user.getPassword().equals(password)) {
            // Le mot de passe fourni n'est pas celui enregistré
            throw new InvalidCredentialsException(username);
        }
        
        return user;
    }

    /**
     * Enregistre un nouvel utilisateur
     * @param user Utilisateur à enregistrer
     */
    public void register(User user) {
        // Insère un nouvel utilisateur en BDD
        userRepository.create(user);
    }
}
