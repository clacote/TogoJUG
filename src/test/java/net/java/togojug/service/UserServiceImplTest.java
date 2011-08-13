package net.java.togojug.service;

import net.java.togojug.domain.User;
import net.java.togojug.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Tests unitaires de la classe {@link UserServiceImpl}
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class UserServiceImplTest {

    // Classe testée
    private UserServiceImpl userServiceImpl;
    
    // Bouchon de la dépendance
    private UserRepository mockUserRepository;
    
    @Before
    public void setUp() throws Exception {
        
        // Création d'un mock-object
        mockUserRepository = mock(UserRepository.class);
        
        userServiceImpl = new UserServiceImpl(mockUserRepository);
    }

    /**
     * Test de {@link UserServiceImpl#login(java.lang.String, java.lang.String) }
     * Cas nominal : login OK
     */
    @Test
    public void testLoginOK() throws LoginException {
        
        final String username = "Toto";
        final String expectedPasword = "password";
        final User expectedUser = new User(username, expectedPasword);
        
        // Paramétrage du mock
        // Mon repository retournera un User issu de la BDD quand je le rechercherai avec un nom donné
        when(mockUserRepository.findByUsername(eq(username))).thenReturn(expectedUser);
        
        // Déclenchement de la méthode testée
        User actualUser = userServiceImpl.login(username, expectedPasword);        
        
        // Vérifications du test
        
        // L'utilisateur retournée par la méthode login doit être le même que celui issu de la BDD
        assertSame(expectedUser, actualUser);
        
        // On peut aussi vérifier qu'on a appelé le bouchon correctement
        verify(mockUserRepository).findByUsername(eq(username));
    }

    /**
     * Test de {@link UserServiceImpl#login(java.lang.String, java.lang.String) }
     * Cas d'erreur : utilisateur inconnu
     */
    @Test(expected=UnknownUserException.class)  // Cette méthode de test attend la levée d'une exception UnknownUserException
    public void testLoginUnknownUser() throws LoginException {
        
        // Mon repository me retournera null, quelque soit le paramètre avec lequel j'appelerai la méthode findByUsername()
        when(mockUserRepository.findByUsername(anyString())).thenReturn(null);

        // Déclenchement de la méthode testée
        try {
            userServiceImpl.login("test", "test");
        } catch (UnknownUserException ex) {
            // Exception attendue.
            // Je la relève pour accepter le test JUnit qui l'attend
            throw ex;
        }
        
        // Si j'atteind cette ligne de code, c'est que je n'ai pas reçu l'exception attendue
        // Je fais donc échouer le test
        fail("Expecting LoginException");
    }

    /**
     * Test de {@link UserServiceImpl#login(java.lang.String, java.lang.String) }
     * Cas d'erreur : mauvais mot de passe
     */
    @Test(expected=InvalidCredentialsException.class)   // Cette méthode de test attend la levée d'une exception UnknownUserException
    public void testLoginInvalidCredential() throws LoginException {
        
        final String username = "Toto";
        final String expectedPasword = "password";
        final User expectedUser = new User(username, expectedPasword);

        // Le repository retournera un utilisateur donné
        when(mockUserRepository.findByUsername(eq(username))).thenReturn(expectedUser);

        try {
            // Déclenchement de la méthode de test
            // Je me logge avec un mot de passe différent
            userServiceImpl.login(username, "zefzef");
        } catch (InvalidCredentialsException ex) {
            // Exception attendue.
            throw ex;
        }
        fail("Expecting InvalidCredentialsException");
    }

    /**
     * Test de {@link UserServiceImpl#register(net.java.togojug.domain.User) }
     * Cas nominal : enregistrement OK.
     */
    @Test
    public void testRegister() throws LoginException {
        
        final User user = new User("toto", "password");

        // Appel de la méthode testée
        userServiceImpl.register(user);        
        
        // On ne contente de vérifier qu'on a appelé le Repository avec le bon paramètre : le même utilisateur à enregistrer
        // Si on impacte le code de UserServiceImpl.register() en supprimant l'appel au repository, on détectera automatiquement la régression
        verify(mockUserRepository).create(same(user));
    }

    /**
     * Test de {@link UserServiceImpl#register(net.java.togojug.domain.User) }
     * Cas d'erreur : erreur technique à l'enregistrement en BDD.
     */
    @Test
    public void testRegisterDatabaseException() throws LoginException {
        
        final User user = new User("toto", "password");
        final Exception databaseException = new RuntimeException("SQL exception");  // Unchecked exception

        // Lorsqu'on appelera le Repository, il lèvera une exception donnée
        doThrow(databaseException).when(mockUserRepository).create(same(user));
        
        try {
            // Appel de la méthode testée
            userServiceImpl.register(user);
        } catch (Exception e) {
            
            // Exception attendue
            // On vérifie que c'est bien celle retournée par le Repository
            assertSame(databaseException, e);
            return;
        }

        fail("Expected exception");
    }
}
