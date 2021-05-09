package com.alaer.lib.api.bean;

import java.util.List;

public class MessageList {

    public int count;
    public List<Message> rows;

    public static class Message {
        public int id;
        public String createTime;
        public String title;
        public String content;
        public int status;
        public int type;
    }

}
