package dto;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

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
