package com.example.projekt_wilk;


    public class ForumTopic {
        private String title;
        private String description;

        // Empty constructor needed for Firestore to automatically map data to this object
        public ForumTopic() {}

        // Constructor with parameters
        public ForumTopic(String title, String description) {
            this.title = title;
            this.description = description;
        }

        // Getters and setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
