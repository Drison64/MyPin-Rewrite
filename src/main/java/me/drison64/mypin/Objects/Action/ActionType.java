package me.drison64.mypin.Objects.Action;

public enum ActionType {

    INTERACT("interact"),
    WAIT("wait"),
    CONSOLE_COMMAND("console_command"),
    PLAYER_COMMAND("player_command");

    private String fancyname;

    ActionType(String fancyname) {
        this.fancyname = fancyname;
    }
}
