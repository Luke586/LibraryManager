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
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String genre;
    private Long copiesOfBook;




}





