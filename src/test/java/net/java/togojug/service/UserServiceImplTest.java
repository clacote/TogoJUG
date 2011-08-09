package net.java.togojug.service;

import net.java.togojug.domain.User;
import net.java.togojug.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sun.security.util.Password;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit tests for {@link UserServiceImpl}
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class UserServiceImplTest {

    // Tested object
    private UserServiceImpl userServiceImpl;
    
    private UserRepository mockUserRepository;
    
    @Before
    public void setUp() throws Exception {
        mockUserRepository = mock(UserRepository.class);
        
        userServiceImpl = new UserServiceImpl(mockUserRepository);
    }


    @Test
    public void testLoginUnknownUser() throws LoginException {
        
        final String username = "Toto";
        when(mockUserRepository.findByUsername(eq(username))).thenReturn(null);

        try {
            userServiceImpl.login(username, "password");
        } catch (UnknownUserException ex) {
            return;
        }
        fail("Expecting LoginException");
    }

    @Test
    public void testLoginInvalidCredential() throws LoginException {
        
        final String username = "Toto";
        final String expectedPasword = "password";
        final User expectedUser = new User(username, expectedPasword);

        when(mockUserRepository.findByUsername(eq(username))).thenReturn(expectedUser);

        try {
            userServiceImpl.login(username, "zefzef");
        } catch (InvalidCredentialsException ex) {
            return;
        }
        fail("Expecting InvalidCredentialsException");
    }

    @Test
    public void testLoginOK() throws LoginException {
        
        final String username = "Toto";
        final String expectedPasword = "password";
        final User expectedUser = new User(username, expectedPasword);
        
        when(mockUserRepository.findByUsername(eq(username))).thenReturn(expectedUser);
        
        User actualUser = userServiceImpl.login(username, "password");        
        assertSame(expectedUser, actualUser);
        
        verify(mockUserRepository).findByUsername(eq(username));
    }

    @Test
    public void testRegister() throws LoginException {
        
        final User user = new User("toto", "password");

        userServiceImpl.register(user);        
        
        verify(mockUserRepository).create(same(user));
    }

    @Test
    public void testRegisterDatabaseException() throws LoginException {
        
        final User user = new User("toto", "password");
        final Exception databaseException = new RuntimeException("SQL exception");

        doThrow(databaseException).when(mockUserRepository).create(same(user));
        
        try {
            userServiceImpl.register(user);
        } catch (Exception e) {
            assertSame(databaseException, e);
            return;
        }

        fail("Expected exception");
    }
}
