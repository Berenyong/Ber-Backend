package bssm.ber.domain.users;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.posts.comment.FreePostsComment;
import bssm.ber.domain.posts.comment.MajorPostsComment;
import bssm.ber.domain.posts.comment.ManagerPostsComment;
import bssm.ber.domain.posts.comment.ShareMajorPostsComment;
import bssm.ber.domain.posts.posts.FreePosts;
import bssm.ber.domain.posts.posts.MajorPosts;
import bssm.ber.domain.posts.posts.ManagerPosts;
import bssm.ber.domain.posts.posts.ShareMajorPosts;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    // Member Update
    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateGitLink(String gitLink) {
        this.gitLink = gitLink;
    }

    public void updateBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }

    // 패스워드 암호화
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    // 회원가입시 패스워드 일치 확인
    public boolean checkPassword(PasswordEncoder passwordEncoder, String checkPassword) {
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    //== 회원탈퇴 -> 작성한 게시물, 댓글 모두 삭제 ==//
    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<FreePosts> freePostsList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<FreePostsComment> freePostsCommentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = ALL, orphanRemoval = true)
    private List<MajorPosts> majorPostsList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<MajorPostsComment> majorPostsCommentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = ALL, orphanRemoval = true)
    private List<ManagerPosts> managerPostsList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<ManagerPostsComment> managerPostsCommentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "users", cascade = ALL, orphanRemoval = true)
    private List<ShareMajorPosts> shareMajorPostsList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "writer", cascade = ALL, orphanRemoval = true)
    private List<ShareMajorPostsComment> shareMajorPostsCommentList = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public void addFreePost(FreePosts post){
        this.getFreePostsList().add(post);
    }

    public void addComment(FreePostsComment comment){
        // comment 의 writer 설정은 comment에서 합니다.
        freePostsCommentList.add(comment);
    }

    public void addPost(MajorPosts post){
        // post 의 writer 설정은 post에서 합니다.
        majorPostsList.add(post);
    }

    public void addComment(MajorPostsComment comment){
        // comment 의 writer 설정은 comment에서 합니다.
        majorPostsCommentList.add(comment);
    }

    public void addPost(ManagerPosts post){
        // post 의 writer 설정은 post에서 합니다.
        managerPostsList.add(post);
    }

    public void addComment(ManagerPostsComment comment){
        // comment 의 writer 설정은 comment에서 합니다.
        managerPostsCommentList.add(comment);
    }

    public void addPost(ShareMajorPosts post){
        // post 의 writer 설정은 post에서 합니다.
        shareMajorPostsList.add(post);
    }

    public void addComment(ShareMajorPostsComment comment){
        // comment 의 writer 설정은 comment에서 합니다.
        shareMajorPostsCommentList.add(comment);
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
