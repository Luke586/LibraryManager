package dto;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String surname;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Book> books;

}
