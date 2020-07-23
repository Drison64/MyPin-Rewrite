package me.drison64.mypin.Objects;

public enum ErrorsEnum {

    COMMAND_ONLY_PLAYER("This command is only for players."),
    COMMAND_ONLY_CONSOLE("This command is only for console.");

    private String errorString;

    ErrorsEnum(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
