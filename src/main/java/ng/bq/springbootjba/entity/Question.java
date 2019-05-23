package ng.bq.springbootjba.entity;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "QUESTION")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String good_answer;
    @Column
    private Integer is_active;
}
