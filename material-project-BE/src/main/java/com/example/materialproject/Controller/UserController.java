package com.example.materialproject.Controller;

import com.example.materialproject.pojo.ApiResponse;
import com.example.materialproject.pojo.Users;
import com.example.materialproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }
    @GetMapping("/api/user")
    public ResponseEntity<?> user(){
        return ResponseEntity.status(200).body(new ApiResponse("Hello User",200));
    }

    @GetMapping("/api/admin")
    public ResponseEntity<?> admin(){
        return ResponseEntity.status(200).body("Hello Admin");
    }

    @GetMapping("/api/users/createDef")
    public ResponseEntity<?> createDef(){
        return ResponseEntity.status(200).body(userService.createDefault());
    }
    @GetMapping(path = "api/userById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(userService.getUser(id));
    }

    @PostMapping("/api/user/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid Users user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("New User added",200));
    }
    @PutMapping ("api/user/update")
    public ResponseEntity<?> replaceUser(@RequestBody @Valid Users user, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message,400));
        }

        userService.upDateUser(user);
        return ResponseEntity.status(200).body(new ApiResponse(" User update",200));
    }
    @DeleteMapping("/api/user/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }

}