package org.ntutssl.document;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.junit.Before;
import org.junit.Test;

public class DocumentParserTest {
    private DocumentParser parser = new DocumentParser();
    private JsonObject titleJson;
    private JsonObject paragraphJson;
    private JsonObject articleJson;

    @Before
    public void setup() {
        String titleJsonString = 
        "{" + 
           "\"type\": \"title\"," +
           "\"text\": \"I'm a simple title\"," +
           "\"size\": 1" +
        "}";

        String paragraphJsonString = 
        "{" + 
           "\"type\": \"paragraph\"," +
           "\"text\": \"I'm a simple paragraph\"" +
        "}";

        String articleJsonString = 
        "{" +
        "    \"type\": \"article\"," +
        "    \"text\": \"I'm a simple article\"," +
        "    \"level\": 1," +
        "    \"contents\": [" +
        "        {" +
        "            \"type\": \"title\"," +
        "            \"text\": \"inner title\"," +
        "            \"size\": 2" +
        "        }," +
        "        {" +
        "            \"type\": \"paragraph\"," +
        "            \"text\": \"inner paragraph\"" +
        "        }" +
        "    ]" +
        "}";
        titleJson = new Gson().fromJson(titleJsonString, JsonObject.class);
        paragraphJson = new Gson().fromJson(paragraphJsonString, JsonObject.class);
        articleJson = new Gson().fromJson(articleJsonString, JsonObject.class);
    }

    @Test
    public void parserTitle() {
        Document result = this.parser.parse(this.titleJson);

        assertEquals("I'm a simple title", result.getText());
    }

    @Test
    public void parserParagraph() {
        Document result = this.parser.parse(this.paragraphJson);

        assertEquals("I'm a simple paragraph", result.getText());
    }

    @Test
    public void parserArticle() {
        Document result = this.parser.parse(this.articleJson);

        assertEquals("I'm a simple article", result.getText());

        Iterator<Document> it = result.iterator();

        assertEquals("inner title", it.next().getText());
        assertEquals("inner paragraph", it.next().getText());
    }

    // @Test
    // public void parserArticleWithAnotherArticle() {
    //     Document result = this.parser.parse(this.articleJson2);

    //     assertEquals("Test article 2", result.getText());

    //     Iterator<Document> it = result.iterator();

    //     assertEquals("Test title", it.next().getText());
    //     assertEquals("Test paragraph", it.next().getText());

    //     Document article2 = it.next();

    //     assertEquals("Test article 3", article2.getText());

    //     Iterator<Document> it2 = article2.iterator();

    //     assertEquals("Test title 2", it2.next().getText());
    //     assertEquals("Test paragraph 2", it2.next().getText());
    // }

}
