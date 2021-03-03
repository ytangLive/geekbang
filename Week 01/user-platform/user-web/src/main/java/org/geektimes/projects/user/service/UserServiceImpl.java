package org.geektimes.projects.user.service;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserServiceImpl implements UserService {

    private DatabaseUserRepository userRepository;


    public UserServiceImpl(){
        DBConnectionManager connectionManager = new DBConnectionManager();
        this.userRepository = new DatabaseUserRepository(connectionManager);
    }

    @Override
    public boolean register(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        DBConnectionManager connectionManager = new DBConnectionManager();
        DatabaseUserRepository userRepository = new DatabaseUserRepository(connectionManager);

        User user = new User();
        user.setName("Test");
        user.setPassword("123");
        user.setEmail("tang@163.com");
        user.setPhoneNumber("18600000");
        userRepository.save(user);


    }
}
