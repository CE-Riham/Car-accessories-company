package model;

public class User {
    private String username, firstName, lastName, phoneNumber, password, email, imagePath;
    private Address address;
    public User(){
        this.username    = "";
        this.firstName   = "";
        this.lastName    = "";
        this.phoneNumber = "";
        this.password    = "";
        this.email       = "";
        this.imagePath   = "";
        this.address     = null;
    }
    public User(String username, String firstName, String lastName,
                String phoneNumber, String password, String email,
                String imagePath, Address address) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setEmail(email);
        setImagePath(imagePath);
        setAddress(address);
    }
    public User(User user){
        this(user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getPhoneNumber(), user.getPassword(), user.getEmail(),
                user.getImagePath(), user.getAddress());
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
        return (imagePath.equals("")?"/assets/usersPictures/nouser.png":imagePath);
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