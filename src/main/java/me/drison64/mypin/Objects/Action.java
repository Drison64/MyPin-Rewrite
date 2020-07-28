package me.drison64.mypin.Objects;

public abstract class Action {

    private String data;
    private ActionType type;

    public Action(String data, ActionType type) {
        this.data = data;
        this.type = type;
        run();
    }

    protected abstract void run();

}
