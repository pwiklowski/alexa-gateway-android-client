package com.wiklosoft.alexagatewayclient;

import android.util.Log;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


/**
 * Created by pwiklowski on 07.05.17.
 */

@ClientEndpoint
public class AlexaGatewayClient {
    private static final String TAG = "AlexaGatewayClient";
    WebSocketContainer container = ContainerProvider.getWebSocketContainer();
    Session mSession = null;

    public void connect(){
        try{
            container.connectToServer(this, new URI("wss://alexa.wiklosoft.com/connect"));
        }catch (URISyntaxException e){
            e.printStackTrace();
        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnOpen
    public void onOpen(Session userSession) {
        Log.d(TAG, "onOpen");
        this.mSession = userSession;
    }
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        Log.d(TAG, "onClose");
        this.mSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        Log.d(TAG, "onMessage");
        this.mSession.getAsyncRemote().sendText(message);

    }

}
