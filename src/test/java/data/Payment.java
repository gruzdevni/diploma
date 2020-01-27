package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Payment {
    private String id;
    private int amount;
    private String created;
    private String status;
    private String transaction_id;
}
