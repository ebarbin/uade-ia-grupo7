package ar.edu.uade.ia.ejb;

import ar.edu.uade.ia.ejb.interfaces.UserManagerLocal;
import ar.edu.uade.ia.ejb.interfaces.UserManagerRemote;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
@LocalBean
public class UserManager implements UserManagerRemote, UserManagerLocal {

    /**
     * Default constructor. 
     */
    public UserManager() {
        // TODO Auto-generated constructor stub
    }

}
