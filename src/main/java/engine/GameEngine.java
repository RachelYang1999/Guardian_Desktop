package engine;

import model.Entity;
import model.User;

import java.io.IOException;
import java.util.List;

public interface GameEngine {
    Entity checkServerStatus();

    Entity login(String userName, String token);

    Entity signUp(String inputUserName) throws IOException;

    List<Entity> getMyLoan(String token, String userName) throws IOException;

    List<Entity> getAvailableLoan(String token) throws IOException;

    Entity requestNewLoan(String userName, String token) throws IOException;

    User getUser();

    Entity payOffLoan(String userName, String token, String loanID) throws IOException;

    List<Entity> getAvailableShip(String token, String shipClass);

    Entity buyShip(String userName, String token, String location, String type);

    List<Entity> getShips(String userName, String token);

    Entity getAShip(String userName, String token, String shipId);

    Entity getMarketInfo(String userName, String token, String locationSymbol);

    Entity buyGood(String userName, String token, String shipId, String goodName, String quantity);

    Entity getAvailableFlightPlans(String userName, String token, String symbol);

    Entity createFlightPlan(String userName, String token, String shipId, String destination);
    void userLogOut();

    Entity buyFuel(String username, String token, String shipID, String quantity);
}

