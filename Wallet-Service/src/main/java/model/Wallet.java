package model;


import jakarta.persistence.Entity;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
public class Wallet {
    private String name;
    private Integer roll_No;
}
