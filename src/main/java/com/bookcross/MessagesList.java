package com.bookcross;

public class MessagesList {

    private String name, userName, lastMessage, profilPic;

    private int unseenMessages;

    public MessagesList(String name, String userName, String lastMessage, String profilPic, int unseenMessages) {
        this.name = name;
        this.userName = userName;
        this.lastMessage = lastMessage;
        this.profilPic = profilPic;
        this.unseenMessages = unseenMessages;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getProfilPic() {
        return profilPic;
    }

    public int getUnseenMessages() {
        return unseenMessages;
    }
}
