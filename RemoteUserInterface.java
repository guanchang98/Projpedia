import java.rmi.Remote;
import java.util.Set;

/**
 * This interface contains methods that could be used by the Client program.
 * It extends the Remote class to support Java RMI feature.
 */
public interface RemoteUserInterface extends Remote {
    /**
     * The method to add/update key-value pair saved in the server.
     * @param key the key to add/update
     * @param value the corresponding value of the key
     * @return execution response from the server
     * @throws Exception possible exception
     */
    String put(String key, String value) throws Exception;

    /**
     * The method to get the value of certain key.
     * @param key the key
     * @return the value of the key
     */
    String get(String key) throws Exception;

    /**
     * The method to delete a key-value pair on the server.
     * @param key the key of the key-value pair to be deleted
     * @return execution response from the server
     * @throws Exception possible exception
     */
    String delete(String key) throws Exception;

    /**
     * This method is used to get the entry-key set that contains all entries.
     * @return the entry set
     * @throws Exception any possible exception
     */
    Set<String> getEntryKeyList() throws Exception;

    /**
     * This method is used to check whether the user is able to edit the entry or not.
     * Only one user will be allowed to edit the entry at a time to ensure data consistency.
     * @param entryKey the key of the entry
     * @return true if user is able to edit; false otherwise
     * @throws Exception any possible exception
     */
    boolean ableToEdit(String entryKey) throws Exception;

    /**
     * This method is used to change the editing status of the entry.
     * @param key the key of the entry
     * @param isEdit the updated status. either true or false
     * @throws Exception any possible exception
     */
    void setEditing(String key, boolean isEdit) throws Exception;

    /**
     * This method is used by the client program to log in.
     * @param userName the username
     * @param userPwd the password
     * @return login response
     * @throws Exception any possible exception
     */
    String login(String userName, String userPwd) throws Exception;

    /**
     * This method is used by the client program to sign up for a new account.
     * @param userName the new account username
     * @param userPwd the new account password
     * @return sign up response
     * @throws Exception any possible exception
     */
    String signup(String userName, String userPwd) throws Exception;

    /**
     * This method is used by the client program to log out the account.
     * @throws Exception any possible exception
     */
    void logout() throws Exception;
}
