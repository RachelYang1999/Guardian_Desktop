package utility;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SpaceTradersOnlineUtil {
    private OkHttpClient client;
    private Response response;
    private String token;

    public String checkServerStatus() {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/game/status")
                    .method("GET", null)
                    .build();
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                responseData = response.body().string();
            }
            System.out.println("SpaceTraderAPI checkServerStatus() status code: " + response.code());
            System.out.println("SpaceTraderAPI checkServerStatus() response " + responseData);

        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String signUp(String userName) throws IOException {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/token")
                    .method("POST", body)
                    .build();
            response = client.newCall(request).execute();
            if (response.code() == 201) {
                responseData = response.body().string();
            }
            System.out.println("SpaceTraderAPI signUp response.code() " + response.code());
//            System.out.println("Response from API: " + response.body().string());
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String login(String userName, String token) {
        String responseData = "";
        try {
            client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName)
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                responseData = response.body().string();
            }
            System.out.println("SpaceTraderAPI login response.code() " + response.code());
            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        } finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getMyLoan(String userName, String token) {
        System.out.println("SpaceTraderAPI getMyLoan userName: " + userName);
        System.out.println("SpaceTraderAPI getMyLoan token: " + token);

        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/loans")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                responseData = response.body().string();
                System.out.println("SpaceTraderAPI getMyLoan responseData " + responseData);
            } else {
                System.out.println("Not 200 \nSpaceTraderAPI getMyLoan responseData " + responseData);
            }

            System.out.println("SpaceTraderAPI getMyLoan response.code() " + response.code());
            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getAvailableLoans(String token) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/game/loans")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                responseData = response.body().string();
            }
            System.out.println("SpaceTraderAPI getAvailableLoans responseData " + responseData);
            System.out.println(new JSONObject(responseData).getJSONArray("loans").toString());
            JSONArray array = new JSONArray(new JSONObject(responseData).getJSONArray("loans").toString());
            System.out.println("array[0]: "+ array.get(0));
            System.out.println(array.toString());

            System.out.println("SpaceTraderAPI getAvailableLoans response.code() " + response.code());
            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String requestNewLoan(String userName, String token) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/loans?type=STARTUP")
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI requestNewLoan responseData " + responseData);
            System.out.println("SpaceTraderAPI getAvailableLoans response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String payOffLoan(String userName, String token, String loanID) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/loans/" + loanID)
                    .method("PUT", body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI requestNewLoan responseData " + responseData);
            System.out.println("SpaceTraderAPI getAvailableLoans response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getAvailableShips(String token, String shipClass) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/game/ships")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            if (shipClass != null && !shipClass.equals("")) {
                request = new Request.Builder()
                        .url("https://api.spacetraders.io/game/ships?class=" + shipClass)
                        .method("GET", null)
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
            }


            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAvailableShips responseData " + responseData);
            System.out.println("SpaceTraderAPI getAvailableShips response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String buyShip(String username, String token, String location, String type) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + username + "/ships?location=" + location + "&type=" + type)
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI buyShip responseData " + responseData);
            System.out.println("SpaceTraderAPI buyShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getShips(String userName, String token) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/ships")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getShips responseData " + responseData);
            System.out.println("SpaceTraderAPI getShips response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getAShip(String userName, String token, String shipId) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/ships/" + shipId)
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAShip responseData " + responseData);
            System.out.println("SpaceTraderAPI getAShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getMarketInfo(String userName, String token, String locationSymbol) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/game/locations/" + locationSymbol + "/marketplace")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAShip responseData " + responseData);
            System.out.println("SpaceTraderAPI getAShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String buyFuel(String userName, String token, String shipId, String quantity) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/purchase-orders?shipId=" + shipId + "&good=fuel&quantity=" + quantity)
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAShip responseData " + responseData);
            System.out.println("SpaceTraderAPI getAShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String buyGood(String userName, String token, String shipId, String goodName, String quantity) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/purchase-orders?shipId=" + shipId + "&good=" + goodName + "&quantity=" + quantity)
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAShip responseData " + responseData);
            System.out.println("SpaceTraderAPI getAShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }

    public String getAvailableFlightPlans(String userName, String token, String symbol) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/game/systems/" + symbol + "/flight-plans")
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAShip responseData " + responseData);
            System.out.println("SpaceTraderAPI getAShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
    }


    public String createFlightPlan(String userName, String token, String shipId, String destination) {
        String responseData = "";
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.spacetraders.io/users/" + userName + "/flight-plans?shipId=" + shipId + "&destination=" + destination)
                    .method("POST", body)
                    .addHeader("Authorization", "Bearer "+ token)
                    .build();

            response = client.newCall(request).execute();
            responseData = response.body().string();

            System.out.println("SpaceTraderAPI getAShip responseData " + responseData);
            System.out.println("SpaceTraderAPI getAShip response.code() " + response.code());

            response.body().close();
            client.connectionPool().evictAll();
        } catch (Exception e) {
            System.err.println("Something got wrong");
            e.printStackTrace();
        }
        finally {
            if (response != null && client != null) {
                response.body().close();
                client.connectionPool().evictAll();
            }
        }
        return responseData;
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
//        new SpaceTradersOnlineUtil().getShips("rachel",
//                "768b2b8b-b6e3-4960-8dc8-1a96200be3b2");
//
//        new SpaceTradersOnlineUtil().getAShip("rachel",
//                "768b2b8b-b6e3-4960-8dc8-1a96200be3b2",
//                "ckoqtx6ed765915s6lt4f8wou");

        new SpaceTradersOnlineUtil().buyFuel("rachel",
                "768b2b8b-b6e3-4960-8dc8-1a96200be3b2",
                "ckoqtx6ed765915s6lt4f8wou",
                "1");

    }


}
