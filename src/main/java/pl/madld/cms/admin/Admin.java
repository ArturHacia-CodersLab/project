package pl.madld.cms.admin;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = Admin.TABLE_NAME)
public class Admin {
    public static final String TABLE_NAME = "admins";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    private String username;
    private String password;
    private int enabled;
    private String firstname;
    private String lastname;
    private String role;
}
