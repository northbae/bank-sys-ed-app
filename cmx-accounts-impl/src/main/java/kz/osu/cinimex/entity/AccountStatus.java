package kz.osu.cinimex.entity;

public enum AccountStatus {
    REQUESTED_BY_USER,
    REQUEST_SENT_TO_CFT,
    CHECKS_STARTED,
    CHECKS_COMPLETED,
    CHECKS_FAILED,
    REQUEST_REJECTED,
    REQUEST_REJECTED_BY_CFT,
    OPENED,
    BLOCKED,
    CLOSED
}
