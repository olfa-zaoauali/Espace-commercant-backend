package com.PFE.Espacecommercant.Authen.users;

public class UserMapper {
    public static User toUser(Admin admin){
        User user = new User();
        user.setEmail(admin.getEmail());
        user.setPassword(admin.getPassword());
        user.setRole(Role.ADMIN);
        return (user);
    }
}
