package br.com.tasks.taskapi.entity;

import br.com.tasks.taskapi.entity.enums.Category;
import br.com.tasks.taskapi.entity.enums.Priority;
import br.com.tasks.taskapi.entity.enums.Status;
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
@Table(name = "task")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "The title cannot be blank.")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "The description cannot be blank.")
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Status status = Status.TODO;

    @Column(name = "category", nullable = false)
    private Category category;

    @Column(name = "priority", nullable = false)
    private Priority priority;

    @ManyToMany()
    @JsonIgnoreProperties(value = "tasks")
    @JoinTable(name = "task_user",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> responsible = new ArrayList<>();

    public void assign(User user) {
        this.responsible.add(user);
    }
}
