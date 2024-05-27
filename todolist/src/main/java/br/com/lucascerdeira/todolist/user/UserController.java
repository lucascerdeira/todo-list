package br.com.lucascerdeira.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;




/**
     * Modificador
     * public -  publico para outras classes 
     * private - restrição 
     * protected - na mesma estrutura do pacote
     */
// aqui podemos trocar o "class" por enum por exemplo 
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // ele vai gerenciar o ciclo de vida
    private UserRepository userRepository;
    
    /*
     * Body
     */
    @PostMapping("/register")
    public UserModel create(@RequestBody UserModel userModel){

        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            System.out.println("Usuário já cadastrado");
            return null;
        }

        var userCreated  = this.userRepository.save(userModel);
        return userCreated;

        
        /*System.out.println("Username:" + userModel.getUsername());
        System.out.println("Name:" + userModel.getName());
        System.out.println("PassWord:" + userModel.getPassword());*/
    }
}
