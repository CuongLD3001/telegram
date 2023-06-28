package vn.tripi.telegram.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "chat_id")
    private String chatId;

    public User(String chatId, String username) {
        this.chatId = chatId;
    }

}

