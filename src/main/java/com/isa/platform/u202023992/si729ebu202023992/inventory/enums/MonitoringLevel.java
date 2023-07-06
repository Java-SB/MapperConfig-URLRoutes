package com.isa.platform.u202023992.si729ebu202023992.inventory.enums;

public enum MonitoringLevel {
    ESSENTIAL_MONITORING(1),
    ADVANCE_MONITORING(2);
    private final int level;
    MonitoringLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return level;
    }
}
