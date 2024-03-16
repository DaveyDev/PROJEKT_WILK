package com.example.projekt_wilk;


    public class ForumTopic {
        private String title;
        private String description;
        private String chatroomId;

        // Constructor


        // Getter method for chatroomId


        // Empty constructor needed for Firestore to automatically map data to this object
        public ForumTopic() {}

        // Constructor with parameters
        public ForumTopic(String title, String description, String chatroomId) {
            this.title = title;
            this.description = description;
            this.chatroomId = chatroomId;
        }

        // Getters and setters
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getChatroomId() { return chatroomId; }
        public void setChatroomId(String chatroomId) { this.chatroomId = chatroomId; }


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
