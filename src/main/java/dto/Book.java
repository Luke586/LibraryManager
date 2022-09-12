package dto;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String genre;
    private Long copiesOfBook;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Customer> customer;




}





