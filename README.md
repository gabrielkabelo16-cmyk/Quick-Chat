# QuickChat - POE Part 1 & 2

Console chat app built for my POE. Java + Maven + JUnit.

## What it does

**Part 1 - Login/Registration (Login.java)**
- Register with a username (needs _ and max 5 chars), password (8+ chars,
  capital, number, special char) and SA cell number (needs international code)
- Login checks your details against what you registered with

**Part 2 - Messages (Message.java, QuickChat.java)**
- Only works after you log in
- Menu: send messages / show recent (coming soon) / quit
- You choose how many messages to send
- Each message gets a random 10-digit ID and a hash (like 00:0:HITHANKS)
- Messages are capped at 250 characters
- Can send, disregard (press 0) or store to a json file

## Run it

```
mvn compile exec:java -Dexec.mainClass=com.quickchat.QuickChat
```

## Run the tests

```
mvn test
```

Tests run automatically on GitHub (see Actions tab) whenever code is pushed.

## References

- Cell number regex: stackoverflow.com/questions/19605444
- JUnit: junit.org/junit5/docs/current/user-guide/
- Storing messages in JSON: used FileWriter (researched gson/json-simple too)
