package br.com.tasks.taskapi.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;
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

    @NotBlank(message = "name cannot be blank.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "cannot be blank.")
    @Size(min = 4, message = "cannot be less than 4.")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany
    private List<Task> tasks;
}
