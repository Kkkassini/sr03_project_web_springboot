package ng.bq.springbootjba.entity;

import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String mail;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String company;
    @Column
    private String phone;
    @Column
    private String creation_date;
    @Column
    private Integer is_admin;
    @Column
    private Integer is_active;

    @JsonIgnore
    public User getUser() {
        return this;
    }

}
