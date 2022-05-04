package dat.startcode.model.services;

import javax.servlet.http.HttpServletRequest;

public class Authentication
{
    public static boolean isRoleAllowed(String role, HttpServletRequest request)
    {
        // Todo: extract user object from session scope and check role

        return true;
    }
}
