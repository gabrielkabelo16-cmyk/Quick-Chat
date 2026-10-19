package com.quickchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {

    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;

    private static List<Message> sentMessages = new ArrayList<>();

    public Message() {
    }

    public Message(String messageID, String recipient, String message) {
        this.messageID = messageID;
        this.recipient = recipient;
        this.message = message;
    }

    // message ID must be 10 chars or less
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        }
        return false;
    }

    // reuse the same check as Login - number needs international code
    public boolean checkRecipientCell() {
        if (recipient.matches("^\\+[1-9]\\d{9,13}$")) {
            return true;
        }
        return false;
    }

    // hash = first 2 digits of ID : message number : first+last word (caps)
    // e.g. 00:0:HITHANKS
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = message.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String hash = firstTwo + ":" + (numMessagesSent - 1) + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // user picks: 1 = send, 2 = disregard, 3 = store
    public String sendMessage(int choice) {
        if (choice == 1) {
            numMessagesSent++;
            messageHash = createMessageHash();
            sentMessages.add(this);
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            numMessagesSent++;
            messageHash = createMessageHash();
            sentMessages.add(this);
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // checks the 250 character limit
    public String checkMessageLength() {
        if (message.length() > 250) {
            int extra = message.length() - 250;
            return "Message exceeds 250 characters by " + extra
                    + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    // prints all messages sent while the program is running
    public String printMessages() {
        String all = "";
        for (Message m : sentMessages) {
            all += "Message ID: " + m.messageID + "\n";
            all += "Message Hash: " + m.messageHash + "\n";
            all += "Recipient: " + m.recipient + "\n";
            all += "Message: " + m.message + "\n\n";
        }
        return all;
    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }

    // stores the message in a json file (researched - filewriter approach)
    public void storeMessage() {
        String json = "{messageID:" + messageID + ",recipient:" + recipient
                + ",message:" + message + "}";
        try {
            java.io.FileWriter fw = new java.io.FileWriter("messages.json", true);
            fw.write(json + System.lineSeparator());
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println("Could not store message.");
        }
    }

    // random 10 digit message ID for tracking
    public static String generateMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += rand.nextInt(10);
        }
        return id;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public int getNumMessagesSent() {
        return numMessagesSent;
    }

    public void setNumMessagesSent(int numMessagesSent) {
        this.numMessagesSent = numMessagesSent;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageHash() {
        return messageHash;
    }
}
