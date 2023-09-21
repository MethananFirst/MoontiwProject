package ssi2.moontiw_announcement.repositories;


import ssi2.moontiw_announcement.entities.Users;

import java.util.List;

public interface UserRepository extends CustomRepository<Users, Integer> {
    List<Users> findAllByOrderByIdAsc();
    Users findByUsername(String username);
    Users findUserByName(String name);
    Users findUserByEmail(String name);
    Users findUserByUsername(String name);
    Users findPasswordByUsername(String username);


    Users findUserByNameAndIdNot(String name, String id);
    Users findUserByEmailAndIdNot(String name, String id);
    Users findUserByUsernameAndIdNot(String name, String id);
}
