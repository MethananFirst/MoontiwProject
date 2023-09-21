package ssi2.moontiw_announcement.controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ssi2.moontiw_announcement.dtos.AddUserDTO;
import ssi2.moontiw_announcement.dtos.UpdateUserDTO;
import ssi2.moontiw_announcement.dtos.UserDTO;
import ssi2.moontiw_announcement.dtos.UserMatchDTO;
import ssi2.moontiw_announcement.entities.Users;
import ssi2.moontiw_announcement.repositories.UserRepository;
import ssi2.moontiw_announcement.services.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public List<Users> getAllUser() {
        List<Users> users = service.getAllUser();
        return modelMapper.map(users, List.class);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById( @PathVariable Integer id) {
        Users user = service.getUserById(id);
        return modelMapper.map(user, UserDTO.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addNewUser(@Valid @RequestBody AddUserDTO users) {
        return service.addNewUser(users);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable int id,@Valid @RequestBody UpdateUserDTO updateUser) {
        return service.updateUser(id, updateUser);
    }

    @PostMapping("/match")
    public UserMatchDTO matchPasswordUser(@RequestBody UserMatchDTO users){
        return service.matchPasswordUser(users);
    }

}
