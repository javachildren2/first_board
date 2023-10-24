package Healthlog.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name="user_info")
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String pwd;
    private String user_id;

    @Builder
    public UserEntity(
            String id,
            String pwd,
            String user_id) {
        this.id = id;
        this.pwd = pwd;
        this.user_id = user_id;
    }
}