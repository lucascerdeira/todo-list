package br.com.lucascerdeira.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.Column;
 /*
     * ID
     * User (ID_USER)
     * User
     * Descrption
     * Title
     * Start date 
     *  End date
     * Priority
     */

@Data
@Entity(name = "tb_task")
public class TaskModel {
    
     @Id
     @GeneratedValue(generator = "UUID")
     private UUID id;
     private String description;

     @Column(length = 50)
     private String title;

     @Column(name = "STARTAT")
     private LocalDateTime startAt;

     @Column(name = "ENDAT")
     private LocalDateTime endAt;

     @Column(name = "PRIORITY")
     private String priority;
    
     @Column(name = "IDUSER")
     private UUID idUser;

     @CreationTimestamp
     private LocalDateTime createdAt;

}