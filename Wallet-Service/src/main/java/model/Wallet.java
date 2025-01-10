package model;



import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Getter
public class Wallet {
    private String name;
    private Integer roll_No;
}
