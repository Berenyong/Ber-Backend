package bssm.ber.domain.entity.users;

import bssm.ber.domain.BaseTimeEntity;
import bssm.ber.domain.entity.posts.FreePosts;
import bssm.ber.domain.entity.posts.MajorPosts;
import bssm.ber.domain.entity.posts.ManagerPosts;
import bssm.ber.domain.entity.posts.ShareMajorPosts;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "user")
    private List<FreePosts> freePosts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<MajorPosts> majorPosts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ManagerPosts> managerPosts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ShareMajorPosts> shareMajorPosts = new ArrayList<>();

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
