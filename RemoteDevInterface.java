import java.rmi.Remote;

/**
 * This interface contains all methods that is used by the Server class.
 * It provides definitions for all development purpose methods.
 */
public interface RemoteDevInterface extends Remote {
    /**
     * Any server notify the coordinator via this function
     * @param action the action from the server
     * @param key the key from the server
     * @param value the value of the key from the server
     * @return 1 means succeed; 0 means fail
     * @throws Exception required exception for all RPC
     */
    int coordinatorReceiveChange(String action, String key, String value) throws Exception;

    /**
     * Coordinator notify all the servers via this function
     * @param action the action to be done
     * @param key the key to be used
     * @param value the value to be used if any
     * @return 1 means succeed; 0 means fail
     * @throws Exception required exception for all RPC
     */
    int otherServerReceiveChange(String action, String key, String value) throws Exception;

    /**
     * Coordinator let all the server commit the change
     * @return 1 means succeed; 0 means fail
     * @throws Exception required exception for all RPC
     */
    int commitChange() throws Exception;

    /**
     * This method is used by any server to notify the coordinator server that there is a user data change.
     * @param action the data change action
     * @param key the new key
     * @param value the new value
     * @return status
     * @throws Exception any possible exceptions
     */
    int coordinatorReceiveUserChange(String action, String key, String value) throws Exception;

    /**
     * Coordinator notify all the servers via this function
     * @param action the action to be done
     * @param key the key to be used
     * @param value the value to be used if any
     * @return 1 means succeed; 0 means fail
     * @throws Exception required exception for all RPC
     */
    int otherServerReceiveUserChange(String action, String key, String value) throws Exception;

    /**
     * Coordinator let all the server commit the change
     * @return 1 means succeed; 0 means fail
     * @throws Exception required exception for all RPC
     */
    int commitUserChange() throws Exception;

    /**
     * This method is used during election.
     * It changes the server's role to coordinator.
     * @return status
     * @throws Exception any possible exception
     */
    int changeCoordinator() throws Exception;

    /**
     * This method is used during election to start an election process.
     * @return election result
     * @throws Exception any possible exceptions
     */
    String election() throws Exception;

    /**
     * This method is used during election to remove the original coordinator.
     * @return removal status
     * @throws Exception any possible exceptions
     */
    int removeMyCoordinator() throws Exception;
}
