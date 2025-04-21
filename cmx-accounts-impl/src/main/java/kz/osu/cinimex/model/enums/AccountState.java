package kz.osu.cinimex.model.enums;

public enum AccountState {
    NONE("None"),
    REQUESTED_BY_USER("Запрошено открытие счета"),
    REQUEST_SENT_TO_CFT("Внутренние проверки банк"),
    CHECKS_STARTED("Внутренние проверки банка"),
    OPENED("Счет открыт"),
    CHECKS_COMPLETED("Проверки банка пройдены"),
    CHECKS_FAILED("Проверки банка не пройдены, требуются ручные проверки"),
    REQUEST_REJECTED("Открытие счета отменено"),
    REQUEST_REJECTED_BY_CFT("Отказано в открытии счета"),
    BLOCKED("Счет заблокирован"),
    CLOSED("Счет закрыт");

    public final String label;

    private AccountState(String label) {
        this.label = label;
    }
}
