package engine;

import factory.entityfactory.*;
import model.Entity;
import model.ExistLoan;
import model.ExistShip;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import utility.SpaceTradersOfflineUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfflineGameEngineImpl implements GameEngine{
    private EntityFactory entityFactory;
    private User user;
    private SpaceTradersOfflineUtil spaceTradersUtil;

    public OfflineGameEngineImpl() {
        this.spaceTradersUtil = new SpaceTradersOfflineUtil();
    }

    public Entity checkServerStatus() {
        entityFactory = new ServerFactory();
        String response = spaceTradersUtil.checkServerStatus();
        return entityFactory.createEntity(response);
    }

    public Entity login(String userName, String token) {

        if (user != null && user.getUsername().equals(userName) && user.getToken().equals(token)) {
            user.setLoginStatus(true);
            System.out.println("GameEngine login did not recreate user!");

            return this.user;
        }
        entityFactory = new LoginUserFactory();
        String response = spaceTradersUtil.login(userName, token);
        System.out.println("GameEngine login response: "+ response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else if (responseJsonObject.has("user")) {
            user = (User) entityFactory.createEntity(response);
            user.setLoginStatus(true);
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
        String response = spaceTradersUtil.getAvailableLoans(this.user);

        System.out.println("GameEngine getMyLoan: " + response);

        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            List<Entity> errorInfoList = new ArrayList<>();
            errorInfoList.add(entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString()));
            return errorInfoList;
        } else if (responseJsonObject.has("loans")) {
            entityFactory = new AvailableLoanFactory();
            JSONArray loanArray = new JSONObject(response).getJSONArray("loans");
            String loanArrayStringType = loanArray.toString();
            return entityFactory.createEntities(loanArrayStringType);
        } else {
            return null;
        }
    }



    public Entity requestNewLoan(String userName, String token) throws IOException {

        String response = spaceTradersUtil.requestNewLoan(this.user);
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
            Entity existLoanEntityType = entityFactory.createEntity(responseJsonObject.getJSONObject("loan").toString());
            this.user.setCredit(responseJsonObject.getInt("credits"));
            this.user.addLoan((ExistLoan) existLoanEntityType);
            return existLoanEntityType;
        } else {
            return null;
        }
    }

    public List<Entity> getMyLoan(String userName, String token) throws IOException {
        entityFactory = new ExistLoanFactory();

        String response = spaceTradersUtil.getMyLoan(this.user);
        System.out.println("GameEngine getMyLoan: " + response);

        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            List<Entity> errorInfoList = new ArrayList<>();
            errorInfoList.add(entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString()));
            return errorInfoList;
        } else if (responseJsonObject.has("loans")) {
            entityFactory = new ExistLoanFactory();
            JSONArray loanArray = new JSONObject(response).getJSONArray("loans");
            String loanArrayStringType = loanArray.toString();
            return entityFactory.createEntities(loanArrayStringType);
        } else {
            return null;
        }

    }

    public Entity payOffLoan(String userName, String token, String loanID) throws IOException {

        String response = spaceTradersUtil.payOffLoan(this.user);
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
        String response = spaceTradersUtil.getAvailableShips(user, "");
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

        String response = spaceTradersUtil.buyShip(user, location, type);
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

            Entity existShipEntityType = entityFactory.createEntity(responseJsonObject.getJSONObject("ship").toString());
            this.user.addShip((ExistShip) existShipEntityType);
            return existShipEntityType;
        } else {
            return null;
        }
    }

    @Override
    public List<Entity> getShips(String userName, String token) {
/*
        Note that if there is Error object, it passed by the type of list not only the object
         */
        List<Entity> result = new ArrayList<>();

        entityFactory = new AvailableShipFactory();
        String response = spaceTradersUtil.getShips(user);
        System.out.println("OfflineGameEngine getShips: " + response);
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
            entityFactory = new ExistShipFactory();
            return entityFactory.createEntities(responseJsonObject.getJSONArray("ships").toString());
        }

        else {
            return null;
        }
    }

    @Override
    public Entity getAShip(String userName, String token, String shipId) {
        String response = spaceTradersUtil.getAShip(user, shipId);
        System.out.println("GameEngine getAShip: " + response);
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

            Entity existShipEntityType = entityFactory.createEntity(responseJsonObject.getJSONObject("ship").toString());
            this.user.addShip((ExistShip) existShipEntityType);
            return existShipEntityType;
        } else {
            return null;
        }    }

    @Override
    public Entity getMarketInfo(String userName, String token, String locationSymbol) {
        String response = spaceTradersUtil.getMarketInfo(user, locationSymbol);
        System.out.println("GameEngine getAShip: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else {
            entityFactory = new MarketPlaceFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        }
    }

    @Override
    public Entity buyGood(String userName, String token, String shipId, String goodName, String quantity) {
        String response = spaceTradersUtil.buyGood(user, shipId, goodName, quantity);
        System.out.println("GameEngine buyFuel: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else {
            entityFactory = new ResponseFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        }
    }



    @Override
    public Entity buyFuel(String userName, String token, String shipId, String quantity) {
        String response = spaceTradersUtil.buyFuel(user, shipId, quantity);
        System.out.println("GameEngine buyFuel: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else {
            entityFactory = new ResponseFactory();

            return entityFactory.createEntity(responseJsonObject.toString());
        }
    }

    @Override
    public Entity getAvailableFlightPlans(String userName, String token, String symbol) {
        String response = spaceTradersUtil.getAvailableFlightPlans(user, symbol);
        System.out.println("GameEngine getAvailableFlightPlans: " + response);
        JSONObject responseJsonObject = new JSONObject(response);
        /*
        Check if the response is error message
         */
        if (responseJsonObject.has("error")) {
            System.out.println();
            entityFactory = new ErrorInfoFactory();
            return entityFactory.createEntity(responseJsonObject.getJSONObject("error").toString());
        } else {
            entityFactory = new ResponseFactory();
            return entityFactory.createEntity(responseJsonObject.toString());
        }
    }

    @Override
    public Entity createFlightPlan(String userName, String token, String shipId, String destination) {
        return null;
    }

    public void userLogOut() {
        this.user.setLoginStatus(false);
    }

    public User getUser()  {
        return this.user;
    }

}
