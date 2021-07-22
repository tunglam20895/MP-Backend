package itsol.mp.app.entities.enums;

import java.util.stream.Stream;

public enum UserStatus {
    ACTIVE(0), DEACTIVATE(2), PENDING(1);

    private final int UserStatus;

    private UserStatus(int UserStatus) {
        this.UserStatus = UserStatus;
    }

    public int getStatus(Long status) {
        return UserStatus;
    }

    public static UserStatus of(Long UserStatus) {
        return Stream.of(itsol.mp.app.entities.enums.UserStatus.values())
                .filter(p -> p.getStatus(UserStatus) == UserStatus)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
