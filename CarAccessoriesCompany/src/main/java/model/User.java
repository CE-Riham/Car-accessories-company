package model;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private String imagePath;
    private String userType;
    private Address address;
    public User(){
        this.username    = "";
        this.firstName   = "";
        this.lastName    = "";
        this.phoneNumber = "";
        this.password    = "";
        this.email       = "";
        this.imagePath   = "";
        this.userType    = "";
        this.address     = null;
    }
    public User(String username, String firstName, String lastName,
                String phoneNumber, String password, String email,
                String imagePath, String userType, Address address) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setEmail(email);
        setImagePath(imagePath);
        setUserType(userType);
        setAddress(address);
    }
    public User(User user){
        this(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getPhoneNumber(), user.getPassword(), user.getEmail(),
                user.getImagePath(), user.getUserType(), user.getAddress());
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagePath() {
        return (imagePath.equals("") ? "src/main/resources/assets/usersPictures/nouser.png":imagePath);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", address=" + address +
                '}';
    }
}
