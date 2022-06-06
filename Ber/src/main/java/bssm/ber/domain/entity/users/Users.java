package bssm.ber.domain.entity.users;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.entity.posts.comment.FreePostsComment;
import bssm.ber.domain.entity.posts.posts.FreePosts;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Users extends BaseTimeEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(length = 45, unique = true)
    private String email;

    @Column(length = 45)
    private String nickname;

    private int age;

    @Column(length = 100)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Column(length = 1000)
    private String gitLink;

    @Column(length = 1000)
    private String blogLink;

    //== 회원탈퇴 -> 작성한 게시물, 댓글 모두 삭제 ==//
    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = ALL, orphanRemoval = true)
    private List<FreePosts> postList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<FreePostsComment> commentList = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public void addPost(FreePosts post){
        //post의 writer 설정은 post에서 함
        postList.add(post);
    }

    public void addComment(FreePostsComment comment){
        //comment의 writer 설정은 comment에서 함
        commentList.add(comment);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
