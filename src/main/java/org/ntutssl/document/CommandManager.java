package org.ntutssl.document;

import java.util.Stack;

public class CommandManager {
	private final Stack<Command> commands;
	private final Stack<Command> historyCommands;

	public CommandManager() {
		commands = new Stack<>();
		historyCommands = new Stack<>();
	}

	public void executeCmd(Command cmd) {
		commands.push(cmd);
		cmd.execute();
	}

	public void undoCmd() {
		// add instruction後才能undo
		if (commands.empty()) return;
		Command command = commands.pop();
		historyCommands.push(command);
		command.undo();
	}

	public void redoCmd() {
		// 有undo過才能redo
		if (historyCommands.empty()) return;
		Command command = historyCommands.pop();
		commands.push(command);
		command.redo(); 
	}
}