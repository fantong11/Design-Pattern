package org.ntutssl.document;

public interface Document {
    public Document getContent();

    public int getLevel();

    public void add(Document document);
}
