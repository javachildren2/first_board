package Healthlog.service;


import Healthlog.domain.UserDto;

public interface UserService {

    Long join(UserDto userDto);
}