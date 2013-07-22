package com.arm.nimbus.sccm.websocket;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Simple chat example
 *
 *
 * @author vicben01
 */
public class SimpleWebSocket extends WebSocketServlet
{
  private ClientsRepository repository;

  @Override
  public void init() throws ServletException
  {
    super.init();
    repository = new ClientsRepository();
  }

  @Override
  protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest httpServletRequest) {
    return new ChatMessageInbound(repository);
  }
}
