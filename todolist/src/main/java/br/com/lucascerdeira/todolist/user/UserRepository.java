package br.com.lucascerdeira.todolist.user;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;



// JpaRepository<CLASSE, TIPO ID>
public interface UserRepository extends JpaRepository<UserModel, UUID>{
    // interface é um contrato de metodos
    UserModel findByUsername(String username);

}
