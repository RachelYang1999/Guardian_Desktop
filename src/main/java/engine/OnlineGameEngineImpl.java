package engine;

import factory.entityfactory.*;
import model.Entity;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import utility.SpaceTradersOnlineUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OnlineGameEngineImpl implements GameEngine{
    private EntityFactory entityFactory;
    private User user;
    private SpaceTradersOnlineUtil spaceTradersUtil;

    /*
    Start
     */

    /*
    End
     */

    public OnlineGameEngineImpl() {
        this.spaceTradersUtil = new SpaceTradersOnlineUtil();
    }

    public Entity checkServerStatus() {

        entityFactory = new ServerFactory();
        String response = spaceTradersUtil.checkServerStatus();
        return entityFactory.createEntity(response);
    }

    public Entity login(String userName, String token) {

        entityFactory = new LoginUserFactory();
        String response = spaceTradersUtil.login(userName, token);
        System.out.println("GameEngine login response: "+ response);
        if (!response.equals("")) {
            user = (User) entityFactory.createEntity(response);
            user.setToken(token);
            return user;
        } else {
            return null;
        }
    }

    public Entity signUp(String userName) throws IOException {

        entityFactory = new SignupUserFactory();

        String response = spaceTradersUtil.signUp(userName);
        if (!response.equals("")) {
            return entityFactory.createEntity(response);
        } else {
            return null;
        }
    }

    public List<Entity> getAvailableLoan(String token) throws IOException {
//        {
//            "loans": [
//            {
//                "type": "STARTUP",
//                    "amount": 200000,
//                    "rate": 40,
//                    "termInDays": 2,
//                    "collateralRequired": false
//            }
//            ]
//        }
        entityFactory = new AvailableLoanFactory();
        String response = spaceTradersUtil.getAvailableLoans(token);
        if (!response.equals("")) {
            System.out.println("GameEngine getAvailableLoan: " + response);

            JSONArray loanArray = new JSONObject(response).getJSONArray("loans");
            String loanArrayStringType = loanArray.toString();
            return entityFactory.createEntities(loanArrayStringType);
        } else {
            return null;
        }
        //
    }

    public List<Entity> getMyLoan(String userName, String token) throws IOException {
        entityFactory = new ExistLoanFactory();

        String response = spaceTradersUtil.getMyLoan(userName, token);

        if (!response.equals("")) {
            System.out.println("GameEngine getMyLoanResponse: " + response);

            JSONArray loanArray = new JSONObject(response).getJSONArray("loans");
            String loanArrayStringType = loanArray.toString();
            return entityFactory.createEntities(loanArrayStringType);
        } else {
            return null;
        }
    }

    public Entity requestNewLoan(String userName, String token) throws IOException {

        String response = spaceTradersUtil.requestNewLoan(userName, token);
        System.out.println("GameEngine requestNewLoan: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("loan")) {
            entityFactory = new ExistLoanFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("loan").toString());
        } else {
            return null;
        }
    }

    public Entity payOffLoan(String userName, String token, String loanID) throws IOException {

        String response = spaceTradersUtil.payOffLoan(userName, token, loanID);
        System.out.println("GameEngine payOffLoan: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        }
        /*
        ------------------------------------------------------------------------------------------------------------------------
        Need further modification since IDK what's the response look like after paying off a loan successfully lol
         */
        else if (responseJsonObject.has("loan")) {
            entityFactory = new ExistLoanFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("loan").toString());
        }
        /*
        ------------------------------------------------------------------------------------------------------------------------
         */
        else {
            return null;
        }

    }

    public List<Entity> getAvailableShip(String token, String shipClass) {
        /*
        Note that if there is Error object, it passed by the type of list not only the object
         */
        List<Entity> result = new ArrayList<>();

        entityFactory = new AvailableShipFactory();
        String response = spaceTradersUtil.getAvailableShips(token, "");
        System.out.println("GameEngine getAvailableShip: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            result.add(entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString()));
            return result;
        }
        else if (responseJsonObject.has("ships")) {
            entityFactory = new AvailableShipFactory();
            return entityFactory.createEntities(responseJsonObject.getJSONArray("ships").toString());
        }

        else {
            return null;
        }
    }

    public Entity buyShip(String userName, String token, String location, String type) {

        String response = spaceTradersUtil.buyShip(userName, token, location, type);
        System.out.println("GameEngine buyShip: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("ship")) {
            entityFactory = new ExistShipFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("ship").toString());
        } else {
            return null;
        }
    }

    public List<Entity> getShips(String userName, String token) {
        /*
        Note that if there is Error object, it passed by the type of list not only the object
         */
        List<Entity> result = new ArrayList<>();

        entityFactory = new AvailableShipFactory();
        String response = spaceTradersUtil.getShips(userName, token);
        System.out.println("GameEngine getShips: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println("[GameEngine] getShips has error");
            entityFactory = new ErrorInfoFactory();
            result.add(entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString()));
            return result;
        }
        else if (responseJsonObject.has("ships")) {
            System.out.println("[GameEngine] getShips responseJsonObject has ships");
            entityFactory = new ExistShipFactory();
            return entityFactory.createEntities(responseJsonObject.getJSONArray("ships").toString());
        }

        else {
            return null;
        }
    }

    public Entity getAShip(String userName, String token, String shipId) {

        String response = spaceTradersUtil.getAShip(userName, token, shipId);
        System.out.println("GameEngine getAShip response: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("ship")) {
            entityFactory = new ExistShipFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("ship").toString());
        } else {
            return null;
        }
    }

    public Entity getMarketInfo(String userName, String token, String locationSymbol) {
        String response = spaceTradersUtil.getMarketInfo(userName, token, locationSymbol);
        System.out.println("GameEngine getAShip response: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        if (responseJsonObject.has("error")) {
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("location")) {
            entityFactory = new MarketPlaceFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("location").toString());
        } else {
            return null;
        }
    }

    public Entity buyFuel(String userName, String token, String shipId, String quantity)  {
        String response = spaceTradersUtil.buyFuel(userName, token, shipId, quantity);
        System.out.println("GameEngine buyFuel response: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        if (responseJsonObject.has("error")) {
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("order")) {
            entityFactory = new ResponseFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        } else {
            return null;
        }
    }

    public Entity buyGood(String userName, String token, String shipId, String goodName, String quantity)  {
        String response = spaceTradersUtil.buyGood(userName, token, shipId, goodName, quantity);
        System.out.println("GameEngine buyGood response: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        if (responseJsonObject.has("error")) {
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("order")) {
            entityFactory = new ResponseFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        } else {
            return null;
        }
    }

    public Entity getAvailableFlightPlans(String userName, String token, String symbol)  {
        String response = spaceTradersUtil.getAvailableFlightPlans(userName, token, symbol);
        System.out.println("GameEngine getAvailableFlightPlans response: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        if (responseJsonObject.has("error")) {
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("flightPlans")) {
            entityFactory = new ResponseFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        } else {
            return null;
        }
    }

    public Entity createFlightPlan(String userName, String token, String shipId, String destination) {
        String response = spaceTradersUtil.createFlightPlan(userName, token, shipId, destination);
        System.out.println("GameEngine createFlightPlan response: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        if (responseJsonObject.has("error")) {
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("flightPlan")) {
            entityFactory = new ResponseFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        } else {
            return null;
        }
    }





    public void userLogOut() {
        this.user.setLoginStatus(false);
        this.user = null;
    }

    public User getUser()  {
        return this.user;
    }

}
