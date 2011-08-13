package net.java.togojug.service;

import net.java.togojug.domain.User;

/**
 * Business interface for {@link User} management
 * @author Sryl <cyril.lacote@gmail.com>
 */
public interface UserService {
    
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
    User login( String username, String password) throws LoginException;

    /**
     * Enregistre un nouvel utilisateur
     * @param user Utilisateur à enregistrer
     */
    void register( User user);
}
