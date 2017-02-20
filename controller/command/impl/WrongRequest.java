package com.epam.parsing.controller.command.impl;


import java.util.List;

import com.epam.parsing.controller.command.Command;
import com.epam.parsing.controller.command.CommandName;

public class WrongRequest implements Command {

	@Override
	public List<?> execute(String request) {
		System.out.println("your command  is wrong!!!");
		System.out.println("Available commands:");
		for(CommandName name:CommandName.values()){
			
			if(name.equals(CommandName.WRONG_REQUEST))
				continue;
			System.out.println(name+",parameter");
		}
		return null;
	}

}
