# Design Patterns 2021 Spring - Lab 2

## Table of Contents
- [Template](#template)
- [Problems](#problems)
  1. [Event-Driven Programming Style](#1-event-driven-programming-style-40%)
  1. [Builder Pattern & Parser](#2-builder-pattern-&-parser-20%)
  1. [Singleton Pattern](#3-Singleton-pattern-10%)
  1. [Composite Pattern](#4-Composite-pattern-10%)
  1. [Iterator Pattern](#5-Iterator-pattern-5%)
  1. [Main](#6-main-20%)
  -  [Scenarios](#scenarios)
  -  [Specs](#Specs)
- [Notes](#notes)
- [Grading Rubrics](#grading-rubrics)
- [File Structure](#file-structure)
- [References](#references)

## Template
Here's the
[template code](https://ntutcc.sharepoint.com/sites/dp2021s/Shared%20Documents/General).<br>
The template is just the skeleton, so you should finish implementation by yourself.

## Problems
### 1. Event-Driven Programming Style (40%)
- Use event-driven programming style to implement a shopping process.
### 2. Builder Pattern & Parser (20%)
- Use builder pattern to build `Goods` objects.
- Use a parser to parse json elements in the imported file.
### 3. Singleton Pattern (10%)
- Use singleton pattern to ensure that `EventManager` has only one instance in 
  the system.
### 4. Composite Pattern (10%)
- Use composite pattern to represent the part-whole relationships between
  `Goods`, `Collection`, and `Merchandise`.
- For lab2, `Goods` will be regarded as the component, `Collection` will be the 
  composite, and `Merchandise` will be seen as the leaf.
### 5. Iterator Pattern (5%)
- Use iterator pattern to access the whole structure of your composite pattern.
- The leaf component should return a `NullIterator`.
### 6.Main (20%)
- Create a shopping system and complete the following scenario.

### Scenarios
You should refer to the following steps to complete your `main` function:
1. import a replenish list -> replenish goods to shop
2. list all goods of shop
3. import shopping list </br>
   -> check stock for each **distinct** goods in shopping list </br>
   -> add goods to shopping cart if in stock
4. list all goods in the shopping cart
5. user decide to pay </br>
   -> complete the purchase process (deduct goods from shop)
6. list the remaining goods of shop after purchase
7. list empty shopping cart

### Specs
Please complete the given code to satisfy all the specs of the `Goods` problem 
and the following conditions.
- interface `Goods`, which should have methods: `id()`, 
  `price()`, `name()`, `description()`, and define the default behaviors for 
  `adding other Goods` and `being accessed by iterator`.
- class `Collection`, `Merchandise`, where both of them are `Goods`. 
- class `Collection`, which can add other `Goods` and its price is the sum of 
  price of its contents.
- class `Merchandise`, which is regarded as leaf.
---
- class `GoodsBuilder`, which helps to build the whole composite structure.
- class `GoodsParser`, which can parse `JsonElement` from a json file and use 
  `GoodsBuilder` to build an `Goods` object.
---
- enum `EventType`, which defines the type of events happened in the system.
- abstract class `Event`, which should have getters of data members: `type` and 
  `data`, and define the default behavior when `getting count`.
- class `GoodsEvent` and `StringEvent`, both of which extends `Event`.
  - the return type of GoodsEvent::type should be `Goods`
  - the return type of StringEvent::type should be `String`
- interface `EventListener`, which contains exactly one function `onEvent` that
  defines different actions according to different published `EventType`.
- class `GoodsParser`, `Shop` and `ShoppingCart`, all of which implement 
  `EventListener`.
- class `EventManager`, which manages any subscription and publication of event,
  that is, it can let an `EventListener` subscribe an `EventType`, or publish a
  specific type of event with data to its subscribers.
---
- class `Main`, which should have exactly one function `main()`. You should 
  follow the above scenario to publish events.
- class `ShopException`, which extends `RuntimeException`. You should 
  use it to handle exception.

## Notes
- Use `System.out.print()` instead of `System.out.println()`.
- `input/replenish_list.json` and `input/shopping_list.json` should be hardcoded
  in `Main`.
- You can assume that the format of the imported lists are correct.
- The format of Goods objects is `%-4s%-22s%-40s%-8s%-6s\n` when listing the 
  items in the shop or in the shopping cart.
- The format of Goods objects is `%-40s%-10s%-10s\n` when issuing a receipt.
- The number of "=" and "-' in the output of the `listShop`, `listCart` and
  receipt is **80**.
- In `Shop`, please print *"This shop does not sell anything."* before you
  import any replenish list.
- In `Shop`, please print *"This store doesn't have this goods."* before you
  import any replenish list.
- In `ShoppingCart`, please print *"Your shopping cart is empty."* when you
  don't add anything into the shopping cart.

## Grading Rubrics
- Event-Driven Programming Style: 40%
- Builder Pattern: 20%
- Singleton Pattern: 10%
- Composite Pattern: 10%
- Iterator Pattern: 5%
- Main: 20%

## File Structure
  ```bash
  .
  ├── README.md
  ├── input
  │   ├── replenish_list.json
  │   └── shopping_list.json
  ├── pom.xml
  └── src
      ├── main
      │   └── java
      │       └── org
      │           └── ntutssl
      │               └── shop
      │                   ├── Collection.java
      │                   ├── Event.java
      │                   ├── EventListener.java
      │                   ├── EventManager.java
      │                   ├── EventType.java
      │                   ├── Goods.java
      │                   ├── GoodsBuilder.java
      │                   ├── GoodsEvent.java
      │                   ├── GoodsParser.java
      │                   ├── InstructionHandler.java
      │                   ├── Main.java
      │                   ├── Merchandise.java
      │                   ├── NullIterator.java
      │                   ├── Shop.java
      │                   ├── ShopException.java
      │                   ├── ShoppingCart.java
      │                   └── StringEvent.java
      └── test
          └── java
              └── org
                  └── ntutssl
                      └── shop
                          ├── GoodsBuilderTest.java
                          ├── GoodsParserTest.java
                          ├── ShopTest.java
                          └── ShoppingCartTest.java
  ```

## References
- [Oracle Java Document](https://docs.oracle.com/en/java/javase/15/docs/api/index.html)
- [Dictionary](https://dictionary.cambridge.org/zht/)
- Your own code from [GitLab](https://ssl-gitlab.csie.ntut.edu.tw)