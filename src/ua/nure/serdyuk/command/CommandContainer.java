package ua.nure.serdyuk.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.view.LoginViewCommand;
import ua.nure.serdyuk.command.view.SignUpViewCommand;

public final class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new HashMap<>();

	static {
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("signUp", new SignUpCommand());
		
		commands.put("findTrains", new FindTrainsCommand());
		commands.put("showRouteInfo", new ShowRouteInfoCommand());
		commands.put("getFreeSeats", new GetFreeSeatsCommand());
		
		commands.put("loginView", new LoginViewCommand());
		commands.put("signUpView", new SignUpViewCommand());

		commands.put("noCommand", new NoCommand());

		LOG.info("Command container initialized");
	}

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.debug("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
	
		return commands.get(commandName);
	}
}
