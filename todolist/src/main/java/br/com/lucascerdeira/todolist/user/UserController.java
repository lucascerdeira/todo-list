package br.com.lucascerdeira.todolist.user;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity create(@RequestBody UserModel userModel){

        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){
            // mensage: Erro
            // status: code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado");
        }
        
        // criptografando a senha do user
        // toCharArray irá transformar em um array de caracteres
        // hashToString = 12 é a força para a senha
        var passwordHashred = BCrypt.withDefaults()
                .hashToString(12, userModel.getPassword().toCharArray());
        
        
        userModel.setPassword(passwordHashred);
    
        var userCreated  = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);

        // retornando  cadastro no terminal  
        /*System.out.println("Username:" + userModel.getUsername());
        System.out.println("Name:" + userModel.getName());
        System.out.println("PassWord:" + userModel.getPassword());*/
    }
}
