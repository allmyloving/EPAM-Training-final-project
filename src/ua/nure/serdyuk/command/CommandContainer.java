package ua.nure.serdyuk.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.admin.AddRouteViewCommand;
import ua.nure.serdyuk.command.admin.AddStationCommand;
import ua.nure.serdyuk.command.admin.AddTrainViewCommand;
import ua.nure.serdyuk.command.admin.AdminViewCommand;
import ua.nure.serdyuk.command.admin.DeleteStationCommand;
import ua.nure.serdyuk.command.admin.ViewStationsCommand;
import ua.nure.serdyuk.command.ajax.GetStationsCommand;
import ua.nure.serdyuk.command.ajax.UpdateStationCommand;
import ua.nure.serdyuk.command.train.FindTrainsCommand;
import ua.nure.serdyuk.command.train.GetFreeSeatsCommand;
import ua.nure.serdyuk.command.train.NoCommand;
import ua.nure.serdyuk.command.train.OrderTicketCommand;
import ua.nure.serdyuk.command.train.OrderTicketViewCommand;
import ua.nure.serdyuk.command.train.PaymentSuccessView;
import ua.nure.serdyuk.command.train.ShowRouteInfoCommand;
import ua.nure.serdyuk.command.user.LoginCommand;
import ua.nure.serdyuk.command.user.LogoutCommand;
import ua.nure.serdyuk.command.user.SignUpCommand;
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

		commands.put("adminView", new AdminViewCommand());
		commands.put("viewStations", new ViewStationsCommand());
		commands.put("addRouteView", new AddRouteViewCommand());
		commands.put("addTrainView", new AddTrainViewCommand());

		commands.put("getStations", new GetStationsCommand());
		commands.put("updateStation", new UpdateStationCommand());
		commands.put("deleteStation", new DeleteStationCommand());
		commands.put("addStation", new AddStationCommand());

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
