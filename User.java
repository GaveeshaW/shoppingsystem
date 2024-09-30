public class User { //defining a class User
    private String username; //declaring variables
    private String password;

    //constructor
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    //getter method of username
    public String getUsername(){
        return username;
    }

    //setter method of username
    public void setUsername(String username){
        this.username = username;
    }

    //getter method of password
    public String getPassword() {
        return password;
    }

    //setter method of password
    public void setPassword(String password) {
        this.password = password;
    }

    //overriding toString to get a string outputpri
    @Override
    public String toString() {
        return super.toString();
    }
}
