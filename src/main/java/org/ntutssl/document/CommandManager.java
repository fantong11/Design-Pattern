package org.ntutssl.document;

import java.util.Stack;

public class CommandManager {
	private final Stack<Command> commands;
	private final Stack<Command> historyCommands;
	private int pointer;

	public CommandManager() {
		commands = new Stack<>();
		historyCommands = new Stack<>();
		pointer = -1;
	}

	public void executeCmd(Command cmd) {
		commands.push(cmd);
		cmd.execute();
	}

	public void undoCmd() {
		Command command = commands.pop();
		historyCommands.push(command);
		command.undo();
	}

	public void redoCmd() {
		Command command = historyCommands.pop();
		commands.push(command);
		command.redo(); 
	}
}