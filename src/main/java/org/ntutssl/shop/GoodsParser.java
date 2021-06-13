package org.ntutssl.shop;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class GoodsParser implements EventListener {

	private GoodsBuilder goodsBuilder = new GoodsBuilder();
	private int count = 0;

	public GoodsParser() {
		EventManager.getInstance().subscribe(EventType.IMPORT_SHOPPING_LIST, this);
		EventManager.getInstance().subscribe(EventType.IMPORT_REPLENISH_LIST, this);
	}

	public void onEvent(Event event) {
		switch (event.type()) {
			case IMPORT_SHOPPING_LIST:
				importShoppingCartList(event);
				break;

			case IMPORT_REPLENISH_LIST:
				importReplenishmentList(event);
				break;

			default:
				break;
		}
	}

	private void importShoppingCartList(Event<String> event) {
		try (JsonReader reader = new JsonReader(new FileReader(event.data()))) {
			Gson gson = new Gson();
			JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				EventManager.getInstance().publish(new GoodsEvent(EventType.CHECK_STOCK, parse(jsonObject), count));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void importReplenishmentList(Event<String> event) {
		try (JsonReader reader = new JsonReader(new FileReader(event.data()))) {
			Gson gson = new Gson();
			JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				EventManager.getInstance().publish(new GoodsEvent(EventType.REPLENISH, parse(jsonObject), count));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Goods parse(JsonObject jsonObj) {
		String type = jsonObj.get("type").getAsString();
		int id = jsonObj.get("id").getAsInt();
		String name = jsonObj.get("name").getAsString();
		String desc = jsonObj.get("description").getAsString();
		try {
			count = jsonObj.get("count").getAsInt();
		} catch (Exception e) {
			// TODO: handle exception
		}

		switch (type) {
			case "merchandise":
				double price = jsonObj.get("price").getAsDouble();
				goodsBuilder.buildMerchandise(id, name, desc, price);
				;
				break;

			case "collection":
				JsonArray jsonArray = new JsonArray();
				jsonArray = jsonObj.get("contents").getAsJsonArray();

				goodsBuilder.startBuildCollection(id, name, desc);
				for (JsonElement jsonElement : jsonArray) {
					parse(jsonElement.getAsJsonObject());
				}

				goodsBuilder.endBuildCollection();
				break;

			default:
				throw new ShopException("Parsing failed.");
		}

		return goodsBuilder.getResult();
	}
}
