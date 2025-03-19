package kz.osu.cinimex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oca_account_transaction")
public class AccountTransaction {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "payer_account", length = 30, nullable = false)
    private String payerAccount;

    @Column(name = "payee_account", length = 30, nullable = false)
    private String payeeAccount;

    @Column(name = "sum", nullable = false)
    private Double sum;

    @Column(name = "is_inner")
    private Boolean isInner;
}
