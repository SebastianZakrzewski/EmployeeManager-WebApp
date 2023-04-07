package com.example.webappbackend.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@DynamicUpdate
@Table(name = "employee")
public class Employee {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String tittle;

    private String phone;

    @Column(name = "url_file")
    @Lob
    private String file;

    @Column(name ="code")
    private int code;
    @Override
    public String toString() {
        return name + " " + email + " " + tittle + " " + phone + " " + code;
    }
}
