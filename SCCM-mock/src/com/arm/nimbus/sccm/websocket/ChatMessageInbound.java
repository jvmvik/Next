package com.arm.nimbus.sccm.websocket;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author vicben01
 */
public class ChatMessageInbound extends MessageInbound
{
  private ClientsRepository repository;

  public ChatMessageInbound(ClientsRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void onTextMessage(CharBuffer message) throws IOException
  {
    repository.sendMessageToAll(message);
  }

  @Override
  protected void onBinaryMessage(ByteBuffer message) throws IOException {
    // no-op
  }


  @Override
  protected void onOpen(WsOutbound outbound) {
    repository.sendMessageToAll(CharBuffer.wrap("New client connected"));
    repository.addClient(this);
  }

  @Override
  protected void onClose(int status) {
    repository.removeClient(this);
    repository.sendMessageToAll(CharBuffer.wrap("Lost a client"));
  }
}
