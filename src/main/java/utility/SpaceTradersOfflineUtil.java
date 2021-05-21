package utility;


import model.Cargo;
import model.ExistShip;
import model.User;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SpaceTradersOfflineUtil {
    private OkHttpClient client;
    private Response response;
    private String token;
    private String userName;

    public String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public String checkServerStatus() {
        JSONObject checkServerStatusJsonObject = new JSONObject();

        checkServerStatusJsonObject.put("status", "spacetraders is currently offline but also available to play");

        return checkServerStatusJsonObject.toString();
    }

    public String signUp(String userName) throws IOException {
        this.userName = userName;
        this.token = "1315f863-de4e-453a-964f-4e2c908bc4dd";

        JSONObject jsonObject = new JSONObject();

        JSONObject userJsonObject = new JSONObject();
        userJsonObject.put("username", userName);
        userJsonObject.put("credits", 0);
        JSONArray shipsJsonArray = new JSONArray();
        userJsonObject.put("ships", shipsJsonArray);
        JSONArray loansJsonArray = new JSONArray();
        userJsonObject.put("loans", loansJsonArray);

        jsonObject.put("token", "1315f863-de4e-453a-964f-4e2c908bc4dd");
        jsonObject.put("user", userJsonObject);

        return jsonObject.toString();
    }

    public String login(String userName, String token) {
        JSONObject jsonObject = new JSONObject();

        if (token.equals(this.token) && userName.equals(this.userName)) {
            JSONObject usernameJsonObject = new JSONObject();
            usernameJsonObject.put("username", userName);
            usernameJsonObject.put("credits", 0);
            JSONArray shipsJsonArray = new JSONArray();
            usernameJsonObject.put("ships", shipsJsonArray);
            JSONArray loansJsonArray = new JSONArray();
            usernameJsonObject.put("loans", loansJsonArray);

            jsonObject.put("user", usernameJsonObject);
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);

            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String getAvailableLoans(User user) {
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            JSONArray loanArray = new JSONArray();

            JSONObject loanJsonObject = new JSONObject();
            loanJsonObject.put("type", "STARTUP");
            loanJsonObject.put("amount", 200000);
            loanJsonObject.put("rate", 40);
            loanJsonObject.put("termInDays", 2);
            loanJsonObject.put("collateralRequired", false);

            loanArray.put(loanJsonObject);

            jsonObject.put("loans", loanArray);
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);

            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }



    public String requestNewLoan(User user) {

        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (user.getLoans().size() == 0) {

                JSONObject loanJsonObject = new JSONObject();
                loanJsonObject.put("id", "ckopushxh2256291ds6pr1b55d4");
                loanJsonObject.put("due", "2021-05-17T14:37:10.464Z");
                loanJsonObject.put("repaymentAmount", 280000);
                loanJsonObject.put("status", "CURRENT");
                loanJsonObject.put("type", "STARTUP");

                jsonObject.put("credits", 200000);
                jsonObject.put("loan", loanJsonObject);
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "Only one loan allowed at a time.");
                errorJsonObject.put("code", 422);
                jsonObject.put("error", errorJsonObject);
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String getMyLoan(User user) {

        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (user.getLoans().size() != 0) {

                JSONArray loanArray = new JSONArray();

                JSONObject loanJsonObject = new JSONObject();
                loanJsonObject.put("id", generateString());
                loanJsonObject.put("due", "2021-05-17T14:37:10.464Z");
                loanJsonObject.put("repaymentAmount", 280000);
                loanJsonObject.put("status", "CURRENT");
                loanJsonObject.put("type", "STARTUP");

                loanArray.put(loanJsonObject);

                jsonObject.put("loans", loanArray);
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "No loans found");
                errorJsonObject.put("code", 422);
                jsonObject.put("error", errorJsonObject);
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String payOffLoan(User user) {
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (user.getLoans().size() != 0) {
                if (user.getLoans().get(0).getRepaymentAmount() <= user.getCredit()) {
                    jsonObject.put("message", "You have paid off this loan");
                } else {
                    JSONObject errorJsonObject = new JSONObject();
                    errorJsonObject.put("message", "Insufficient funds to pay for loan.");
                    errorJsonObject.put("code", 400);
                    jsonObject.put("error", errorJsonObject);
                }
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "No loans found");
                errorJsonObject.put("code", 422);
                jsonObject.put("error", errorJsonObject);
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
        }
        return jsonObject.toString();
    }

    public String getAvailableShips(User user, String shipClass) {

        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            JSONArray shipArray = new JSONArray();

            JSONObject ship1 = new JSONObject();
            ship1.put("type", "HM-MK-I");
            ship1.put("class", "MK-I");
            ship1.put("maxCargo", 50);
            ship1.put("speed", 3);
            ship1.put("manufacturer", "Hermes");
            ship1.put("plating", 20);
            ship1.put("weapons", 5);

            JSONArray locationArray = new JSONArray();
            JSONObject location = new JSONObject();
            location.put("system", "OE");
            location.put("location", "OE-PM-TR");
            location.put("price", 57525);
            locationArray.put(location);

            ship1.put("purchaseLocations", locationArray);

            JSONObject ship2 = new JSONObject();
            ship2.put("type", "EM-MK-I");
            ship2.put("class", "MK-I");
            ship2.put("maxCargo", 50);
            ship2.put("speed", 2);
            ship2.put("manufacturer", "Electrum");
            ship2.put("plating", 5);
            ship2.put("weapons", 10);

            JSONArray locationArray2 = new JSONArray();
            JSONObject location2 = new JSONObject();
            location2.put("system", "OE");
            location2.put("location", "OE-PM-TR");
            location2.put("price", 37750);
            locationArray2.put(location);

            ship2.put("purchaseLocations", locationArray2);

            shipArray.put(ship1);
            shipArray.put(ship2);
            jsonObject.put("ships", shipArray);

        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);

            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String buyShip(User user, String location, String type) {
//        {
//            "credits": 178875,
//            "ship": {
//              "cargo": [],
//              "class": "MK-I",
//                "id": "ckno9324k0079iiop71j5nrob",
//                "location": "OE-PM-TR",
//                "manufacturer": "Jackshaw",
//                "maxCargo": 50,
//                "plating": 5,
//                "spaceAvailable": 50,
//                "speed": 1,
//                "type": "JW-MK-I",
//                "weapons": 5,
//                "x": 23,
//                "y": -28
//        }
//        }
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (location.equals("OE-PM-TR") && type.equals("HM-MK-I")) {
                if (user.getCredit() >= 57525) {
                    user.setCredit(user.getCredit() - 57525);
                    JSONObject shipJsonObject = new JSONObject();
                    JSONArray cargoArray = new JSONArray();
                    shipJsonObject.put("cargo", cargoArray);
                    shipJsonObject.put("id", "ckoqtx6ed765915s6lt4f8wou");
                    shipJsonObject.put("location", "OE-PM-TR");
                    shipJsonObject.put("manufacturer", "Jackshaw");
                    shipJsonObject.put("maxCargo", 50);
                    shipJsonObject.put("plating", 5);
                    shipJsonObject.put("spaceAvailable", 50);
                    shipJsonObject.put("speed", 1);
                    shipJsonObject.put("type", "HM-MK-I");
                    shipJsonObject.put("class", "MK-I");
                    shipJsonObject.put("weapons", 55);
                    shipJsonObject.put("x", 23);
                    shipJsonObject.put("y", -28);

                    jsonObject.put("credits", user.getCredit());
                    jsonObject.put("ship", shipJsonObject);
                } else {
                    JSONObject errorJsonObject = new JSONObject();
                    errorJsonObject.put("message", "No enough fund to buy this ship, you only got " + user.getCredit() + " credits but this ship will cost 57525" );
                    errorJsonObject.put("code", 422);
                    jsonObject.put("error", errorJsonObject);
                }

            } else if (location.equals("OE-PM-TR") && type.equals("EM-MK-I")) {
                if (user.getCredit() >= 37750) {
                    user.setCredit(user.getCredit() - 37750);
                    JSONObject shipJsonObject = new JSONObject();
                    JSONArray cargoArray = new JSONArray();
                    shipJsonObject.put("cargo", cargoArray);
                    shipJsonObject.put("id", "ckoqtx6ed765915s6lt4f8wou");
                    shipJsonObject.put("location", "OE-PM-TR");
                    shipJsonObject.put("manufacturer", "Electrum");
                    shipJsonObject.put("maxCargo", 50);
                    shipJsonObject.put("plating", 5);
                    shipJsonObject.put("spaceAvailable", 50);
                    shipJsonObject.put("speed", 2);
                    shipJsonObject.put("class", "MK-I");
                    shipJsonObject.put("type", "EM-MK-I");
                    shipJsonObject.put("weapons", 10);
                    shipJsonObject.put("x", 20);
                    shipJsonObject.put("y", -8);

                    jsonObject.put("credits", user.getCredit());
                    jsonObject.put("ship", shipJsonObject);
                } else {
                    JSONObject errorJsonObject = new JSONObject();
                    errorJsonObject.put("message", "No enough fund to buy this ship, you only got " + user.getCredit() + " credits but this ship will cost 37750");
                    errorJsonObject.put("code", 422);
                    jsonObject.put("error", errorJsonObject);
                }
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "No suitable ships available");
                errorJsonObject.put("code", 422);
                jsonObject.put("error", errorJsonObject);
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String getShips(User user) {
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (user.getShips().size() != 0) {
                JSONArray shipArray = new JSONArray();

                for (ExistShip ship: user.getShips()) {
                    JSONObject ship1 = new JSONObject();
                    ship1.put("id", ship.getId());
                    ship1.put("location", ship.getLocation());
                    ship1.put("x", ship.getxCoord());
                    ship1.put("y", ship.getyCoord());
                    JSONArray cargoArray = new JSONArray();
                    for (Cargo cargo: ship.getCargo()) {

                    }
                    ship1.put("cargo", cargoArray);
                    ship1.put("spaceAvailable", ship.getSpaceAvailable());
                    ship1.put("type", ship.getType());
                    ship1.put("class", ship.getShipClass());
                    ship1.put("maxCargo", ship.getMaxCargo());
                    ship1.put("speed", ship.getSpeed());
                    ship1.put("manufacturer", ship.getManufacturer());
                    ship1.put("plating", ship.getPlating());
                    ship1.put("weapons", ship.getWeapons());

                    shipArray.put(ship1);
                    jsonObject.put("ships", shipArray);

                }
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "No ships found");
                errorJsonObject.put("code", 40101);

                jsonObject.put("error", errorJsonObject);
            }


        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);

            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String getAShip(User user, String shipID) {
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            ExistShip foundShip = null;
            List<ExistShip> shipList = user.getShips();
            for (ExistShip ship : shipList) {
                if (ship.getId().equals(shipID)) {
                    foundShip = ship;
                }
            }
            if (foundShip != null) {
                JSONObject ship1 = new JSONObject();
                ship1.put("id", foundShip.getId());
                ship1.put("location", foundShip.getLocation());
                ship1.put("x", foundShip.getxCoord());
                ship1.put("y", foundShip.getyCoord());
                JSONArray cargoArray = new JSONArray();
                for (Cargo cargo: foundShip.getCargo()) {

                }
                ship1.put("cargo", cargoArray);
                ship1.put("spaceAvailable", foundShip.getSpaceAvailable());
                ship1.put("type", foundShip.getType());
                ship1.put("class", foundShip.getShipClass());
                ship1.put("maxCargo", foundShip.getMaxCargo());
                ship1.put("speed", foundShip.getSpeed());
                ship1.put("manufacturer", foundShip.getManufacturer());
                ship1.put("plating", foundShip.getPlating());
                ship1.put("weapons", foundShip.getWeapons());

                jsonObject.put("ship", ship1);
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "No matched ship found");
                errorJsonObject.put("code", 422);
                jsonObject.put("error", errorJsonObject);
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
        }

        return jsonObject.toString();
    }

    public String getMarketInfo(User user, String locationSymbol) {
        String result = "";
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus() && locationSymbol.equals("OE-PM-TR")) {
            result = "{\"symbol\":\"OE-PM-TR\",\"marketplace\":[{\"symbol\":\"FUSION_REACTORS\",\"quantityAvailable\":400,\"purchasePricePerUnit\":20411,\"sellPricePerUnit\":20299,\"volumePerUnit\":6,\"pricePerUnit\":20355,\"spread\":56},{\"symbol\":\"CONSTRUCTION_MATERIALS\",\"quantityAvailable\":5504,\"purchasePricePerUnit\":124,\"sellPricePerUnit\":122,\"volumePerUnit\":1,\"pricePerUnit\":123,\"spread\":1},{\"symbol\":\"METALS\",\"quantityAvailable\":63543,\"purchasePricePerUnit\":14,\"sellPricePerUnit\":12,\"volumePerUnit\":1,\"pricePerUnit\":13,\"spread\":1},{\"symbol\":\"FUEL\",\"quantityAvailable\":65768,\"purchasePricePerUnit\":22,\"sellPricePerUnit\":20,\"volumePerUnit\":1,\"pricePerUnit\":21,\"spread\":1}],\"name\":\"Tritus\",\"x\":21,\"structures\":[],\"y\":-24,\"type\":\"MOON\",\"allowsConstruction\":false}";
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
            result = jsonObject.toString();
        }
        return result;
    }

    public String buyFuel(User user, String shipId, String quantity) {
        String result = "";
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (shipId.equals("ckoqtx6ed765915s6lt4f8wou")) {
                result = "{\"credits\":178813,\"ship\":{\"spaceAvailable\":46,\"plating\":5,\"type\":\"JW-MK-I\",\"speed\":1,\"manufacturer\":\"Jackshaw\",\"x\":21,\"maxCargo\":50,\"y\":-24,\"location\":\"OE-PM-TR\",\"id\":\"ckoqtx6ed765915s6lt4f8wou\",\"cargo\":[{\"totalVolume\":4,\"quantity\":4,\"good\":\"FUEL\"}],\"class\":\"MK-I\",\"weapons\":5},\"order\":{\"total\":26,\"quantity\":1,\"good\":\"FUEL\",\"pricePerUnit\":26}}";
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "No ship matched with this ship id, you can try this id \"ckoqtx6ed765915s6lt4f8wou\" again ");
                errorJsonObject.put("code", 40101);
                jsonObject.put("error", errorJsonObject);
                result = jsonObject.toString();
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
            result = jsonObject.toString();
        }
        return result;
    }

    public String buyGood(User user, String shipId, String goodName, String quantity) {
        String result = "";
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (shipId.equals("ckoqtx6ed765915s6lt4f8wou")) {
                if (goodName.equals("METALS")) {
                    result = "{\"credits\":178798,\"ship\":{\"spaceAvailable\":45,\"plating\":5,\"type\":\"JW-MK-I\",\"speed\":1,\"manufacturer\":\"Jackshaw\",\"x\":21,\"maxCargo\":50,\"y\":-24,\"location\":\"OE-PM-TR\",\"id\":\"ckoqtx6ed765915s6lt4f8wou\",\"cargo\":[{\"totalVolume\":1,\"quantity\":1,\"good\":\"METALS\"},{\"totalVolume\":4,\"quantity\":4,\"good\":\"FUEL\"}],\"class\":\"MK-I\",\"weapons\":5},\"order\":{\"total\":15,\"quantity\":1,\"good\":\"METALS\",\"pricePerUnit\":15}}";
                } else {
                    JSONObject errorJsonObject = new JSONObject();
                    errorJsonObject.put("message", "No ship matched with this ship id, you can try this id \"ckoqtx6ed765915s6lt4f8wou\" again ");
                    errorJsonObject.put("code", 40101);
                    jsonObject.put("error", errorJsonObject);
                    result = jsonObject.toString();
                }
            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
                errorJsonObject.put("code", 40101);
                jsonObject.put("error", errorJsonObject);
                result = jsonObject.toString();
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
            result = jsonObject.toString();
        }
        return result;
    }

    public String getAvailableFlightPlans(User user, String symbol) {
        String result = "";
        JSONObject jsonObject = new JSONObject();

        if (user.getLoginStatus()) {
            if (symbol.equals("OE")) {
                result = "{\"flightPlans\":[{\"createdAt\":\"2021-05-16T13:18:04.451Z\",\"destination\":\"OE-CR\",\"shipType\":\"HM-MK-I\",\"arrivesAt\":\"2021-05-16T13:19:16.449Z\",\"id\":\"ckor7emjn4529611ds6w1iznxfe\",\"departure\":\"OE-PM\",\"shipId\":\"ckor5vgpo478761ds6hkh672nu\",\"username\":\"Dunador\"},{\"createdAt\":\"2021-05-16T13:14:57.197Z\",\"destination\":\"OE-KO\",\"shipType\":\"JW-MK-I\",\"arrivesAt\":\"2021-05-16T13:19:00.196Z\",\"id\":\"ckor7am254192581ds6p49h6jsd\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckor7aj0k4186081ds6w68syzkz\",\"username\":\"zealous-moccasin-lungfish-§\"},{\"createdAt\":\"2021-05-16T13:16:47.866Z\",\"destination\":\"OE-CR\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:19:23.864Z\",\"id\":\"ckor7czga4400621ds6k1dsojso\",\"departure\":\"OE-PM\",\"shipId\":\"ckor0jrfy7135541ds66gsnbrty\",\"username\":\"bchoii_xxx\"},{\"createdAt\":\"2021-05-16T13:15:07.922Z\",\"destination\":\"OE-BO\",\"shipType\":\"JW-MK-I\",\"arrivesAt\":\"2021-05-16T13:21:10.921Z\",\"id\":\"ckor7auc24211681ds69lwqsv5b\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckor7as2v4207201ds6r2qq1ur3\",\"username\":\"zealous-moccasin-lungfish-§\"},{\"createdAt\":\"2021-05-16T13:16:31.189Z\",\"destination\":\"OE-UC-AD\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:18:43.938Z\",\"id\":\"ckor7cml14375361ds6r8hcyse4\",\"departure\":\"OE-PM\",\"shipId\":\"ckor60qtb846361ds68gh3b9kd\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:17:23.159Z\",\"destination\":\"OE-PM\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:19:59.157Z\",\"id\":\"ckor7dqon4451611ds6jp6ptu4w\",\"departure\":\"OE-CR\",\"shipId\":\"ckor2aj1u10533731ds67vtyvbly\",\"username\":\"bchoii_xxx\"},{\"createdAt\":\"2021-05-16T13:15:03.554Z\",\"destination\":\"OE-NY\",\"shipType\":\"JW-MK-I\",\"arrivesAt\":\"2021-05-16T13:20:06.553Z\",\"id\":\"ckor7aqyq4204531ds6815pdq53\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckor7ankh4197491ds6w0mn6ggv\",\"username\":\"zealous-moccasin-lungfish-§\"},{\"createdAt\":\"2021-05-16T13:14:38.602Z\",\"destination\":\"OE-PM-TR\",\"shipType\":\"GR-MK-III\",\"arrivesAt\":\"2021-05-16T13:18:41.601Z\",\"id\":\"ckor7a7pm4163881ds6uaa58r5b\",\"departure\":\"OE-KO\",\"shipId\":\"ckor4lsv015120381ds669g2rikw\",\"username\":\"Redcrafter\"},{\"createdAt\":\"2021-05-16T13:18:00.594Z\",\"destination\":\"OE-PM-TR\",\"shipType\":\"GR-MK-II\",\"arrivesAt\":\"2021-05-16T13:18:33.593Z\",\"id\":\"ckor7ejki4521701ds6rffvoz40\",\"departure\":\"OE-PM\",\"shipId\":\"ckor0wzmg7764251ds64374wyc6\",\"username\":\"Redcrafter\"},{\"createdAt\":\"2021-05-16T13:17:57.542Z\",\"destination\":\"OE-UC-OB\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:20:13.291Z\",\"id\":\"ckor7eh7q4515161ds6lgq5vqhe\",\"departure\":\"OE-NY\",\"shipId\":\"ckor6t5se2876821ds62gcd9wcf\",\"username\":\"JimHawkins\"},{\"createdAt\":\"2021-05-16T13:17:58.374Z\",\"destination\":\"OE-PM\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:20.873Z\",\"id\":\"ckor7ehuu4517491ds6ypo4yq2f\",\"departure\":\"OE-KO\",\"shipId\":\"ckor50pnu15812371ds63g88rxbr\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:15:15.494Z\",\"destination\":\"OE-UC\",\"shipType\":\"JW-MK-I\",\"arrivesAt\":\"2021-05-16T13:22:33.493Z\",\"id\":\"ckor7b06e4224831ds6cm0j0ssq\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckor7awjf4216081ds63a5xzn87\",\"username\":\"zealous-moccasin-lungfish-§\"},{\"createdAt\":\"2021-05-16T13:17:43.264Z\",\"destination\":\"OE-PM\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:05.763Z\",\"id\":\"ckor7e6744480861ds6f0jo3eof\",\"departure\":\"OE-KO\",\"shipId\":\"ckor73wkb3668011ds6di20yqmj\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:17:00.977Z\",\"destination\":\"OE-UC-AD\",\"shipType\":\"JW-MK-I\",\"arrivesAt\":\"2021-05-16T13:24:21.966Z\",\"id\":\"ckor7d9kh4421461ds69d4smqq2\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckor7d8fh4418251ds6usj9zyio\",\"username\":\"spcoder\"},{\"createdAt\":\"2021-05-16T13:18:15.420Z\",\"destination\":\"OE-PM-TR\",\"shipType\":\"GR-MK-III\",\"arrivesAt\":\"2021-05-16T13:18:48.419Z\",\"id\":\"ckor7ev0c4554011ds6odkfphe3\",\"departure\":\"OE-PM\",\"shipId\":\"ckor6hsao2098291ds6xku7k75b\",\"username\":\"Redcrafter\"},{\"createdAt\":\"2021-05-16T13:18:07.060Z\",\"destination\":\"OE-KO\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:22:10.059Z\",\"id\":\"ckor7eok44535481ds6wd106f19\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckoqu5sk003741ds6s62ad2do\",\"username\":\"JimHawkins\"},{\"createdAt\":\"2021-05-16T13:16:28.843Z\",\"destination\":\"OE-UC-OB\",\"shipType\":\"ZA-MK-II\",\"arrivesAt\":\"2021-05-16T13:20:30.342Z\",\"id\":\"ckor7ckrv4369481ds6um5zus3j\",\"departure\":\"OE-NY\",\"shipId\":\"ckoqtbk2h213215s65gi5f0z0\",\"username\":\"farlox\"},{\"createdAt\":\"2021-05-16T13:08:02.103Z\",\"destination\":\"OE-NY\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:18:35.102Z\",\"id\":\"ckor71prr3501441ds69e6tttxg\",\"departure\":\"OE-BO\",\"shipId\":\"ckor6dbbh1784201ds68wtn8cb4\",\"username\":\"rihan\"},{\"createdAt\":\"2021-05-16T13:16:25.572Z\",\"destination\":\"OE-CR\",\"shipType\":\"ZA-MK-II\",\"arrivesAt\":\"2021-05-16T13:19:31.571Z\",\"id\":\"ckor7ci904361101ds6l6qgub39\",\"departure\":\"OE-UC-OB\",\"shipId\":\"ckoqzkm2b5330231ds68q4gqjyf\",\"username\":\"farlox\"},{\"createdAt\":\"2021-05-16T13:17:02.582Z\",\"destination\":\"OE-UC\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:26:26.581Z\",\"id\":\"ckor7dat24425521ds6bnjmokkh\",\"departure\":\"OE-BO\",\"shipId\":\"ckoqzxu4s6071141ds6rbm0wsnn\",\"username\":\"rihan\"},{\"createdAt\":\"2021-05-16T13:18:18.828Z\",\"destination\":\"OE-PM-TR\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:42.075Z\",\"id\":\"ckor7exn04561991ds60suce3tx\",\"departure\":\"OE-KO\",\"shipId\":\"ckor78th64053011ds6j0cfnh2w\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:16:12.007Z\",\"destination\":\"OE-PM\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:18:48.006Z\",\"id\":\"ckor7c7s74335101ds62jwqurxl\",\"departure\":\"OE-CR\",\"shipId\":\"ckoqw44dz914961ds6doujd195\",\"username\":\"bchoii_xxx\"},{\"createdAt\":\"2021-05-16T13:18:10.618Z\",\"destination\":\"OE-UC\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:27:34.617Z\",\"id\":\"ckor7eray4543891ds6rv6dob5m\",\"departure\":\"OE-BO\",\"shipId\":\"ckor2sp5q11484961ds6pgag8ojy\",\"username\":\"rihan\"},{\"createdAt\":\"2021-05-16T13:18:18.086Z\",\"destination\":\"OE-PM-TR\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:41.335Z\",\"id\":\"ckor7ex2e4560841ds6rf83yxxm\",\"departure\":\"OE-KO\",\"shipId\":\"ckor78sbr4049251ds6lr69d0jq\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:12:52.276Z\",\"destination\":\"OE-UC\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:22:16.274Z\",\"id\":\"ckor77xo43985541ds6t5htlx7r\",\"departure\":\"OE-BO\",\"shipId\":\"ckoqz31ce4408221ds6cstuswos\",\"username\":\"rihan\"},{\"createdAt\":\"2021-05-16T13:18:16.794Z\",\"destination\":\"OE-KO\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:22:19.793Z\",\"id\":\"ckor7ew2i4557371ds6pwtp4a4z\",\"departure\":\"OE-PM-TR\",\"shipId\":\"ckoqvrer9654301ds6ky8wt87a\",\"username\":\"JimHawkins\"},{\"createdAt\":\"2021-05-16T13:18:17.365Z\",\"destination\":\"OE-UC\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:21:00.863Z\",\"id\":\"ckor7ewid4558591ds6ywjkgosc\",\"departure\":\"OE-BO\",\"shipId\":\"ckor5g4mc16521801ds69wb4z90j\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:16:31.286Z\",\"destination\":\"OE-CR\",\"shipType\":\"ZA-MK-II\",\"arrivesAt\":\"2021-05-16T13:19:37.285Z\",\"id\":\"ckor7cmnq4375891ds66o0h31i0\",\"departure\":\"OE-UC-OB\",\"shipId\":\"ckoqzkk9m5326071ds6p9ntkmp8\",\"username\":\"farlox\"},{\"createdAt\":\"2021-05-16T13:18:17.686Z\",\"destination\":\"OE-CR\",\"shipType\":\"HM-MK-I\",\"arrivesAt\":\"2021-05-16T13:19:29.685Z\",\"id\":\"ckor7ewra4559941ds6roz30okt\",\"departure\":\"OE-PM\",\"shipId\":\"ckor5vlrz486601ds6s89wf4n2\",\"username\":\"Dunador\"},{\"createdAt\":\"2021-05-16T13:17:47.821Z\",\"destination\":\"OE-UC-OB\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:20:03.568Z\",\"id\":\"ckor7e9pp4493061ds628ohu4h9\",\"departure\":\"OE-NY\",\"shipId\":\"ckor6t2e72869511ds6h69zjhcv\",\"username\":\"JimHawkins\"},{\"createdAt\":\"2021-05-16T13:16:02.727Z\",\"destination\":\"OE-CR\",\"shipType\":\"ZA-MK-II\",\"arrivesAt\":\"2021-05-16T13:19:08.725Z\",\"id\":\"ckor7c0mf4317971ds6846aaxwc\",\"departure\":\"OE-UC-OB\",\"shipId\":\"ckoqtbj57206415s66usf8g9y\",\"username\":\"farlox\"},{\"createdAt\":\"2021-05-16T13:13:42.622Z\",\"destination\":\"OE-BO\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:23:06.621Z\",\"id\":\"ckor790im4068921ds6lsuntt0l\",\"departure\":\"OE-UC\",\"shipId\":\"ckoqz3ql34441711ds6revjv103\",\"username\":\"rihan\"},{\"createdAt\":\"2021-05-16T13:17:58.494Z\",\"destination\":\"OE-PM\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:20:34.493Z\",\"id\":\"ckor7ehy64518021ds6el2rdfsu\",\"departure\":\"OE-CR\",\"shipId\":\"ckoqw4dar922201ds6uyh8al35\",\"username\":\"bchoii_xxx\"},{\"createdAt\":\"2021-05-16T13:11:54.997Z\",\"destination\":\"OE-UC-OB\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:19:27.996Z\",\"id\":\"ckor76ph13888301ds6y9xjez8q\",\"departure\":\"OE-NY\",\"shipId\":\"ckor485cx14472541ds6f6k5cofe\",\"username\":\"rihan\"},{\"createdAt\":\"2021-05-16T13:18:19.414Z\",\"destination\":\"OE-BO\",\"shipType\":\"HM-MK-I\",\"arrivesAt\":\"2021-05-16T13:20:19.413Z\",\"id\":\"ckor7ey3a4563751ds65e3kghm1\",\"departure\":\"OE-KO\",\"shipId\":\"ckoqu5t2404231ds6n3dheuy0\",\"username\":\"JimHawkins\"},{\"createdAt\":\"2021-05-16T13:17:29.706Z\",\"destination\":\"OE-NY\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:40.955Z\",\"id\":\"ckor7dvqi4460441ds6wur04jqn\",\"departure\":\"OE-KO\",\"shipId\":\"ckor3ysn913997251ds6dov6bf0o\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:14:59.046Z\",\"destination\":\"OE-PM-TR\",\"shipType\":\"GR-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:02.044Z\",\"id\":\"ckor7anhi4196941ds63cmcs52x\",\"departure\":\"OE-KO\",\"shipId\":\"ckor5v89r467231ds6v7rr3iix\",\"username\":\"Redcrafter\"},{\"createdAt\":\"2021-05-16T13:17:44.011Z\",\"destination\":\"OE-UC-OB\",\"shipType\":\"HM-MK-III\",\"arrivesAt\":\"2021-05-16T13:19:59.759Z\",\"id\":\"ckor7e6rv4482091ds6abq2bpco\",\"departure\":\"OE-NY\",\"shipId\":\"ckor6ljcx2332811ds61s2jaqdr\",\"username\":\"ZERG\"},{\"createdAt\":\"2021-05-16T13:10:57.703Z\",\"destination\":\"OE-NY\",\"shipType\":\"GR-MK-I\",\"arrivesAt\":\"2021-05-16T13:21:30.702Z\",\"id\":\"ckor75h9j3806731ds6lismhs2v\",\"departure\":\"OE-BO\",\"shipId\":\"ckor4xv6115686951ds6te096urv\",\"username\":\"rihan\"}]}";

            } else {
                JSONObject errorJsonObject = new JSONObject();
                errorJsonObject.put("message", "The system symbol does look like correct, please try again");
                errorJsonObject.put("code", 40101);
                jsonObject.put("error", errorJsonObject);
                result = jsonObject.toString();
            }
        } else {
            JSONObject errorJsonObject = new JSONObject();
            errorJsonObject.put("message", "Token was invalid or missing from the request. Did you confirm sending the token as a query parameter or authorization header?");
            errorJsonObject.put("code", 40101);
            jsonObject.put("error", errorJsonObject);
            result = jsonObject.toString();
        }

        return result;
    }



//    public void getUserInfo() throws Exception {
//        this.token = "d105372a-0607-43cf-b13c-902738aa4fd6";
//        try {
//            client = new OkHttpClient().newBuilder()
//                    .build();
//            Request request = new Request.Builder()
//                    .url("https://api.spacetraders.io/users/test05102")
//                    .method("GET", null)
//                    .addHeader("Authorization", "Bearer " + this.token)
//                    .build();
//            response = client.newCall(request).execute();
//
//            String jsonData = response.body().string();
//            System.out.println(jsonData);
//
//            JSONObject jsonObject = new JSONObject(jsonData);
//            JSONObject userObject = jsonObject.getJSONObject("user");
//            System.out.println(userObject.toString());
//            String userName = userObject.getString("username");
//            int credits = userObject.getInt("credits");
//            System.out.println("The username is: " + userName);
//            System.out.println("The username is: " + credits);
//
//            response.body().close();
//            client.connectionPool().evictAll();
//        } catch (Exception e) {
//            System.err.println("Something got wrong");
//            e.printStackTrace();
////            System.err.println(e.getStackTrace().toString());
//
//        }
//        finally {
//            if (response != null && client != null) {
//                response.body().close();
//                client.connectionPool().evictAll();
//            }
//        }
//    }

    public static void main(String[] args) throws Exception {

    }


}
