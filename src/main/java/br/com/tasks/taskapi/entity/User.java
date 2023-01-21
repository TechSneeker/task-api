package br.com.tasks.taskapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "name cannot be blank.")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "responsible")
    @JsonIgnoreProperties(value = "responsible")
    private List<Task> tasks = new ArrayList<>();
}
