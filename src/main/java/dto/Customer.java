package dto;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String surname;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Book> books;

    @Override
    public String toString() {
        return "Customer Id : " + id +
                "\nFirst Name: " + firstName +
                "\nSurname: " + surname +
                "\nAddress: " + address;

    }
}
