package com.arm.nimbus.sccm.websocket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * This repository takes care of registering connected clients and of course removing them when they
 * disconnect.
 */
public class ClientsRepository {
  private List<ChatMessageInbound> clients = new ArrayList<>();

  /**
   * Add a new client to the repository.
   *
   * @param messageInbound ChatMessageInbound that is the actual client
   */
  public void addClient(ChatMessageInbound messageInbound) {
    this.clients.add(messageInbound);
  }

  /**
   * Removes the provided client from the repository.
   *
   * @param messageInbound ChatMessageInbound that is the actual client
   */
  public void removeClient(ChatMessageInbound messageInbound) {
    clients.remove(messageInbound);
  }

  /**
   * Send a message to all clients.
   *
   * @param message CharBuffer containing the message to send to all clients
   */
  public void sendMessageToAll(CharBuffer message) {
    try {
      for (ChatMessageInbound client : clients) {
        CharBuffer buffer = CharBuffer.wrap(message);
        client.getWsOutbound().writeTextMessage(buffer);
        client.getWsOutbound().flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
