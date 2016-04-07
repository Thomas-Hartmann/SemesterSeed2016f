package security;

/**
 *
 * @author tha
 */
public class Tester {
    public static void main(String[] args) {
        try {
            String hash = PasswordStorage.createHash("test");
            System.out.println(hash);
            boolean verifyPassword = PasswordStorage.verifyPassword("test", hash);
            System.out.println("Password is valid: "+verifyPassword);
            System.out.println("----------------------------------------");
            
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex) {
            ex.printStackTrace();
        }
    }
}
