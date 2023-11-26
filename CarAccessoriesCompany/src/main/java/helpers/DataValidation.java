package helpers;

import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {

    public static boolean regexMatcher(String regex, String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static String userValidationTest(User user){
        if(user.getEmail().equals(""))
            return "Email address can't be empty";
        if(user.getPhoneNumber().equals(""))
            return "Phone number can't be empty";
        if(user.getPassword().equals(""))
            return "Password can't be empty";
        if(user.getUsername().equals(""))
            return "Username can't be empty";
        if(user.getFirstName().equals(""))
            return "First name can't be empty";
        if(user.getLastName().equals(""))
            return "Last name can't be empty";
        if(emailValidationTest(user.getEmail())){
            if(phoneNumberValidationTest(user.getPhoneNumber())) {
                if (passwordValidationTest(user.getPassword()))
                    return "Valid";
                return "Invalid password";
            }
            return "Invalid phone number";
        }
        return "Invalid email address";
    }

    public static boolean idTest(String id) {
        if (id.length() == 9) {
            boolean flag = true;
            for (int i = 0; i < id.length(); i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        return false;
    }

    public static boolean phoneNumberValidationTest(String phoneNumber) {
        String regex = "^[0-9]{10}$";
        return regexMatcher(regex, phoneNumber);
    }

    public static  boolean emailValidationTest(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return regexMatcher(regex, email);
    }

    public static boolean passwordValidationTest(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return regexMatcher(regex, password);

    }
    public static int  ordernameTest(String name) {
        int flag=0;
        name=(name.toUpperCase());
        if(name.equals("CARPET")) flag=1;
        else if( name.equals("COVER"))  flag=2;
        else if(name.equals("BLANKET")) flag=3;
        return flag;
    }
    public static boolean orderQuantityTest(String quantity) {
        if ( quantity.length() >0) {
            boolean flag = true;
            for(int i=0;i<quantity.length();i++){
                if(!Character.isDigit(quantity.charAt(i))){
                    flag = false ;
                    break;
                }
            }
            return flag;
        }
        return false;
    }
    public static boolean orderSizeTest(String size) {
        return DataValidation.orderQuantityTest(size);

    }
    public static boolean orderColorTest(String color) {
        boolean flag = true;
        for (int i = 0; i < color.length(); i++) {
            if (Character.isDigit(color.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean orderPictureTest(String picture) {
        return (picture.indexOf(".png")!=-1 || picture.indexOf(".jpg")!=-1) ;
    }
}
