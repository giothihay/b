package com.es.chatapp.Model;

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private boolean isseen;
    private String type;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Chat(String sender, String receiver, String message, boolean isseen, String type) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.isseen = isseen;
        this.type = type;
    }
    //    public Chat(String sender, String receiver, String message, boolean isseen) {
//        this.sender = sender;
//        this.receiver = receiver;
//        this.message = message;
//        this.isseen = isseen;
//    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }
}
