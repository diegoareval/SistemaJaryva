
package com.devs.auxiliar;

public class UserData {
    String Username;
    int id;
    
    public UserData(String Username, int id)
    {
        this.Username = Username;
        this.id  = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
