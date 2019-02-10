package users.webservise.security;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class Autorization {
    private static List<String> tokens = new ArrayList<String>();

    public static void addToken(String token){
        tokens.add(token);
    }
     public static void removeToken(String token) {
        tokens.remove(token);
    }

    public static boolean ifTokenExists(Cookie[] cookies){
        boolean result = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("superuser".equals(cookie.getName())) {
                    if (tokens.contains(cookie.getValue())) {
                        result = true;
                    }
                }
            }
        }
        return result;

    }
}
