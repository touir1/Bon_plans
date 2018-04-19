/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author touir
 */
public class StringUtils {
    private StringUtils(){}
    
    public static boolean isEmpty(String string){
        return (string == null || "".equals(string.trim()));
    }
    
    public static boolean isValidEmail(String emailStr) {
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    public static boolean isValidPwd(String pwdStr) {
        return !isEmpty(pwdStr) && pwdStr.length() > 7;
    }
   
}
