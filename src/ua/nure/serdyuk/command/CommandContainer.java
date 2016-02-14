package ua.nure.serdyuk.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.view.LoginViewCommand;
import ua.nure.serdyuk.command.view.SignUpViewCommand;

/**
 * Container for commands.
 * 
 * @author Daria Serdiuk
 * @see Command
 */
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
		commands.put("orderTicketView", new OrderTicketViewCommand());
		commands.put("orderTicket", new OrderTicketCommand());

		commands.put("loginView", new LoginViewCommand());
		commands.put("signUpView", new SignUpViewCommand());
		commands.put("paymentSuccessfulView", new PaymentSuccessView());

		commands.put("noCommand", new NoCommand());

		LOG.info("Command container initialized");
	}

	/**
	 * Retrieves a command from container, if command is not found,
	 * {@code NoCommand} will be returned.
	 * 
	 * @param commandName
	 *            a key to lookup in the container
	 * @return if exists -- command associated with the {@code commandName},
	 *         otherwise {@code NoCommand}
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.info("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}
}
