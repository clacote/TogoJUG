package net.java.togojug.service;

/**
 * Unknown user trying to log in.
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class UnknownUserException extends LoginException {

    private String username;

    /**
     * Constructs an instance of <code>UnknownUser</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UnknownUserException(String username) {
        super("Unknown user : " + username);
    }
}
