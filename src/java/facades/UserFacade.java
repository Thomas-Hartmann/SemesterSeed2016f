package facades;

import entity.Role;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import security.PasswordStorage;

public class UserFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SemesterSeedPU");

    public UserFacade() {
               
    }
    
    public void addUser(entity.User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserByUserId(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser(String userName, String password) {
        EntityManager em = emf.createEntityManager();
        User user = null;
        boolean authenticated = false;
        try {
            user = em.find(User.class, userName);
            if (user == null) {
                return null;
            }

            authenticated = PasswordStorage.verifyPassword(password, user.getPassword());
            
        } catch (PasswordStorage.CannotPerformOperationException ex) {
            ex.printStackTrace();
        } catch (PasswordStorage.InvalidHashException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return authenticated ? user.getRolesAsStrings() : null;
    }
}
