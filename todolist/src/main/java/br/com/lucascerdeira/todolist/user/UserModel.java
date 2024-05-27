package br.com.lucascerdeira.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//@Getter para uso exclusivo
//@Setter
//automaticamente ele vai colocar todos os getters e setters
@Data
@Entity(name = "tb_users")
public class UserModel {

    // eu posso colocar a anotation em cima do meu atributo se eu quiser
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
   
}
