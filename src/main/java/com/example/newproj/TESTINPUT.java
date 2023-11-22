package com.example.newproj;

public class TESTINPUT {
    private TESTINPUT(){

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

    public static boolean phoneNumberTest(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            boolean flag = true;
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
        return false;
    }

    public static boolean gmailTest(String gmail) {
        if (Character.isDigit(gmail.charAt(0)) || gmail.length() < 17) return false;
        else {
            boolean flag = false;
            for (int i = 1; i < gmail.length(); i++) {
                if (gmail.charAt(i) == '@') flag = true;
            }
            return flag;
        }
    }

    public static boolean passwordTest(String password) {
        boolean flags=false;
        boolean flagc=false;
        boolean flagn=false;
        if( password.length()<8) return false;
        else {
            for (int i = 0; i < password.length(); i++){
                if(Character.isLowerCase(password.charAt(i))) flags=true;
                else if(Character.isUpperCase(password.charAt(i))) flagc=true;
                else if (Character.isDigit(password.charAt(i))) flagn=true;
            }
            return flags&&flagc&&flagn;
        }
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
        return TESTINPUT.orderQuantityTest(size);

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
