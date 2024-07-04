package service;


import entity.User;

public interface AuthenticationService {
     void register();
     User login();
}
