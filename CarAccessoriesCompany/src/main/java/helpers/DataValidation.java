package helpers;

import model.Product;
import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    private static String valid = "Valid";
    private DataValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean regexMatcher(String regex, String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    private static String emptyUserFieldsTest(User user){
        if(user.getEmail().equals("")) return "Email address can't be empty";
        if(user.getPhoneNumber().equals("")) return "Phone number can't be empty";
        if(user.getPassword().equals("")) return "Password can't be empty";
        if(user.getUsername().equals("")) return "Username can't be empty";
        if(user.getFirstName().equals("")) return "First name can't be empty";
        if(user.getLastName().equals("")) return "Last name can't be empty";
        if(user.getUserType().equals("")) return "User type can't be empty";
        return valid;
    }
    public static String productFieldsTest(String name, String longDescription, String shortDescription,
                                           String category, String price, String quantity){
        if(name.equals(""))return "Product name can't be empty";
        if(longDescription.equals(""))return "Product long description can't be empty";
        if(shortDescription.equals(""))return "Product short description can't be empty";
        if(category.equals("null"))return "Product category can't be empty";
        if(price.equals(""))return "Product price can't be empty";
        if(quantity.equals(""))return "Product quantity can't be empty";
        if(!doubleValidationTest(price)) return "Invalid price";
        if(!integerValidationTest(quantity)) return "Invalid quantity";
        return valid;
    }
    public static String productValidationTest(Product product){
        return productFieldsTest(product.getProductName(), product.getLongDescription(), product.getShortDescription(),
                product.getProductCategory(), String.valueOf(product.getProductPrice()), String.valueOf(product.getAvailableAmount()));
    }
    public static String userValidationTest(User user){
        String emptyFieldsTest = emptyUserFieldsTest(user);
        if(!emptyFieldsTest.equals(valid))
                return emptyFieldsTest;
        if(emailValidationTest(user.getEmail())){
            if(phoneNumberValidationTest(user.getPhoneNumber())) {
                if (passwordValidationTest(user.getPassword())) {
                    if (user.getUserType().equals("admin") || user.getUserType().equals("customer") || user.getUserType().equals("installer"))
                        return valid;
                    else {
                        return "Invalid user type";
                    }
                }
                else{
                    return "Invalid password";
                }
            }
            return "Invalid phone number";
        }
        return "Invalid email address";
    }

    public static boolean phoneNumberValidationTest(String phoneNumber) {
        String regex = "^[0-9]{10}$";
        return regexMatcher(regex, phoneNumber);
    }

    public static boolean integerValidationTest(String number){
        String regex = "^[0-9]{1,}$";
        return regexMatcher(regex, number);
    }

    public static boolean doubleValidationTest(String number){
        String regex = "^[0-9]{1,}+.[0-9]{0,2}$";
        return regexMatcher(regex, number) || integerValidationTest(number);
    }

    public static  boolean emailValidationTest(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return regexMatcher(regex, email);
    }

    public static boolean passwordValidationTest(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
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
