package io.hhplus.tdd.architecture.tdd.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column
    private String userId;

    @Column
    private String password;

    @Column
    private String name;

}
