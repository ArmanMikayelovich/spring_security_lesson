package com.energizeglobal.itpm.model.enums;

public enum TaskTrigger {
    BLOCKED_BY,
    TRIGGERED_BY;

    public static <T extends Enum<T>> T valueOf(Class<T> enumType,
                                                String name) {
        if (name == null || name.equals("")) {
            return null;
        } else {
            return Enum.valueOf(enumType, name);
        }
    }

}
