package com.epam.parsing.controller.command;

import java.util.List;

public interface Command {
 public List<?> execute (String request);
}
