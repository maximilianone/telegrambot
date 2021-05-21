package model;

import lombok.Getter;

public enum Command {
    START("/start"),
    HELP("/help"),
    UNKNOWN("unknown");

    @Getter
    private String commandText;

    Command(String commandText) {
        this.commandText = commandText;
    }

    public static Command fromString(String commandText) {
        for (Command command : Command.values()) {
            if (command.getCommandText().equals(commandText)) {
                return command;
            }
        }
        return Command.UNKNOWN;
    }
}
