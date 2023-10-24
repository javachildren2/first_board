package Healthlog.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data			// Getter Setter
@Builder		// DTO -> Entity화
@AllArgsConstructor	// 모든 컬럼 생성자 생성
@NoArgsConstructor	// 기본 생성자
@Table(name = "User")
public class User {

    @Id	// 내가 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동 id 생성
    private String id;

    @Column(nullable = false)
    private String pwd;

    @Column
    private String user_id;

}