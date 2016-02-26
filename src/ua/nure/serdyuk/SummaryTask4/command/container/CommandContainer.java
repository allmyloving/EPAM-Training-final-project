package ua.nure.serdyuk.SummaryTask4.command.container;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.command.Command;
import ua.nure.serdyuk.SummaryTask4.command.admin.*;
import ua.nure.serdyuk.SummaryTask4.command.ajax.*;
import ua.nure.serdyuk.SummaryTask4.command.train.*;
import ua.nure.serdyuk.SummaryTask4.command.user.*;
import ua.nure.serdyuk.SummaryTask4.command.view.*;

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
		commands.put("findTrainsNotDirect", new FindTrainsNotDirectCommand());
		commands.put("showRouteInfo", new ShowRouteInfoCommand());
		commands.put("getFreeSeats", new GetFreeSeatsCommand());
		commands.put("orderTicketView", new OrderTicketViewCommand());
		commands.put("orderTicket", new OrderTicketCommand());

		commands.put("loginView", new LoginViewCommand());
		commands.put("signUpView", new SignUpViewCommand());
		commands.put("userProfileView", new UserProfileViewCommand());
		commands.put("indexView", new IndexViewCommand());
		commands.put("paymentSuccessfulView", new PaymentSuccessView());
		commands.put("error404View", new Error404ViewCommand());

		commands.put("adminView", new AdminViewCommand());
		commands.put("stationView", new StationViewCommand());
		commands.put("routeView", new RouteViewCommand());
		commands.put("trainView", new TrainViewCommand());
		commands.put("carriagesView", new CarriagesViewCommand());

		commands.put("deleteStation", new DeleteStationCommand());
		commands.put("addStation", new AddStationCommand());
		commands.put("getRoutes", new GetRoutesCommand());
		commands.put("addTrain", new AddTrainCommand());
		commands.put("deleteRoute", new DeleteRouteCommand());
		commands.put("addCarriages", new AddCarriagesCommand());
		commands.put("addRoute", new AddRouteCommand());

		commands.put("getStations", new GetStationsCommand());
		commands.put("updateStation", new UpdateStationCommand());
		commands.put("updateCarriage", new UpdateCarriageCommand());

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
