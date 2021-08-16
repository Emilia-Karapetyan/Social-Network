package socket;


import model.User;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@ServerEndpoint("/server")
public class Server {
    static Set<Session> chatConnection = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void addConnection(Session user) {
        chatConnection.add(user);
    }

    @OnMessage
    public void addMessage(String message,Session userSession) throws IOException {
            Iterator<Session> iterator = chatConnection.iterator();
            while (iterator.hasNext()) {
                iterator.next().getBasicRemote().sendText(buildJsonData(message));
            }
        }

    @OnClose
    public void handleClose(Session userSession) {
        chatConnection.remove(userSession);
    }

    private String buildJsonData(String message) {
        JsonObject jsonObject = Json.createObjectBuilder().add("message", message).build();
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stringWriter)) {
            jsonWriter.write(jsonObject);
        }
        return stringWriter.toString();
    }
}
