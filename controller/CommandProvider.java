package com.epam.parsing.controller;

import com.epam.parsing.controller.command.Command;
import com.epam.parsing.controller.command.CommandName;
import com.epam.parsing.controller.command.impl.*;


import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {

		repository.put(CommandName.PARSE_BY_SAX, new ParseBySax());
		repository.put(CommandName.PARSE_BY_STAX, new ParseByStax());
		repository.put(CommandName.PARSE_BY_DOM, new ParseByDom());

		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		

	}

	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
			System.out.println("!!-->"+command);
		} catch (IllegalArgumentException | NullPointerException e) {
			// write log
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}
}
