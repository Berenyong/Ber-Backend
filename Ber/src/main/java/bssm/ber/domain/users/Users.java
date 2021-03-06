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
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

    @Enumerated(EnumType.STRING)
    private Role role;

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

    // ???????????? ?????????
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    // ??????????????? ???????????? ?????? ??????
    public boolean checkPassword(PasswordEncoder passwordEncoder, String checkPassword) {
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    // ??????????????? USER ?????? ??????(Default : USER)
    public void addUserAuthority() {
        this.role = Role.USER;
    }

    //== ???????????? -> ????????? ?????????, ?????? ?????? ?????? ==//
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

    //== ???????????? ????????? ==//
    public void addFreePost(FreePosts post){
        this.getFreePostsList().add(post);
    }

    public void addComment(FreePostsComment comment){
        // comment ??? writer ????????? comment?????? ?????????.
        freePostsCommentList.add(comment);
    }

    public void addPost(MajorPosts post){
        // post ??? writer ????????? post?????? ?????????.
        majorPostsList.add(post);
    }

    public void addComment(MajorPostsComment comment){
        // comment ??? writer ????????? comment?????? ?????????.
        majorPostsCommentList.add(comment);
    }

    public void addPost(ManagerPosts post){
        // post ??? writer ????????? post?????? ?????????.
        managerPostsList.add(post);
    }

    public void addComment(ManagerPostsComment comment){
        // comment ??? writer ????????? comment?????? ?????????.
        managerPostsCommentList.add(comment);
    }

    public void addPost(ShareMajorPosts post){
        // post ??? writer ????????? post?????? ?????????.
        shareMajorPostsList.add(post);
    }

    public void addComment(ShareMajorPostsComment comment){
        // comment ??? writer ????????? comment?????? ?????????.
        shareMajorPostsCommentList.add(comment);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role.name()));
        log.info("role.name : " + role.name());

        return auth;
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
