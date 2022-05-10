package dat.startcode.model.services;

import javax.servlet.http.HttpServletRequest;

public class Authentication {
    public static boolean isRoleAllowed(String role, HttpServletRequest request) {
        if(!(request.getSession().getAttribute(role) ==null)){
            return request.getSession().getAttribute(role).equals(role);
        }

        return true;
    }
}
