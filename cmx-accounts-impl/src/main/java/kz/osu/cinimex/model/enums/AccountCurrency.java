package kz.osu.cinimex.model.enums;

import lombok.Getter;

@Getter
public enum AccountCurrency {
    RUB("Рубли", 810),
    RUR("Рубли при осуществлении международных расчетов", 643),
    USD("Доллары", 840),
    EUR("Евро", 978),
    CNY("Юани", 156);

    private final String label;
    private final int number;

    AccountCurrency(String label, int number) {
        this.label = label;
        this.number = number;
    }
}
