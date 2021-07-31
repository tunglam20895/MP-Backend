package itsol.mp.app.entities.enums;

import java.util.stream.Stream;

public enum UserType {
    EMPLOYEE(0), INTERN(1), COLLABORATOR(2);

    private final int UserType;

    private UserType(int UserType) {
        this.UserType = UserType;
    }

    public int getType(Long type) {
        return UserType;
    }

    public static UserType of(Long UserType) {
        return Stream.of(itsol.mp.app.entities.enums.UserType.values())
                .filter(p -> p.getType(UserType) == UserType)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
