package bssm.ber.domain.entity.posts;

import bssm.ber.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FreePosts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "freePosts_id")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT", length = 2000)
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
