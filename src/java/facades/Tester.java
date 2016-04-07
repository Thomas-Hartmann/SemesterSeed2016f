package facades;

import entity.Role;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.Login;
import security.PasswordStorage;

/**
 *
 * @author tha
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SemesterSeedPU");
        EntityManager em = emf.createEntityManager();
        UserFacade uf = new UserFacade();
//        try {
//          createUser(uf);
//        } catch (PasswordStorage.CannotPerformOperationException ex) {
//            ex.printStackTrace();
//        }
        List<String> roles = uf.authenticateUser("admin", "test");
        for (String role : roles) {
            System.out.println(role);
        }
    }

    private static void createUser(UserFacade uf) throws PasswordStorage.CannotPerformOperationException {
        User user = new User();
        User admin = new User();
        user.setUserName("user");
        admin.setUserName("admin");
        
        String hashedPwd = PasswordStorage.createHash("test");
        user.setPassword(hashedPwd);
        admin.setPassword(hashedPwd);
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setRoleName("User");
        role2.setRoleName("Admin");
        user.addRole(role1);
        admin.addRole(role2);
        uf.addUser(user);
        uf.addUser(admin);
    }
}
