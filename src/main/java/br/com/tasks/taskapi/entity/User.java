package br.com.tasks.taskapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "name cannot be empty.")
    @NotBlank(message = "name cannot be blank.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "password cannot be empty.")
    @NotBlank(message = "password cannot be blank.")
    @Size(min = 4, message = "password cannot be less than 4")
    @Column(name = "password", nullable = false)
    private String password;
}
