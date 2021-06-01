package org.ntutssl.shop;

public interface EventListener {
  public <T> void onEvent(Event<T> event);
}
