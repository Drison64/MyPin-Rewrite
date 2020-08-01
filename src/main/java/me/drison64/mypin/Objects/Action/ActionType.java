package me.drison64.mypin.Objects.Action;

public enum ActionType {

    INTERACT("interact", Action_Interact.class),
    WAIT("wait", Action_Wait.class),
    CONSOLE_COMMAND("console_command", Action_ConsoleCommand.class),
    PLAYER_COMMAND("player_command", Action_PlayerCommand.class);

    private String fancyname;
    private Class<? extends Action> clazz;

    public String getFancyname() {
        return fancyname;
    }

    public Class<? extends Action> getClazz() {
        return clazz;
    }

    ActionType(String fancyname, Class<? extends Action> clazz) {
        this.fancyname = fancyname;
        this.clazz = clazz;
    }
}
