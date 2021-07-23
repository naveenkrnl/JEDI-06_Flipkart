package com.flipkart.exception;

public class UserNotApprovedException extends Exception {
        private int userId;

        public UserNotApprovedException(int userId){
            this.userId = userId;
        }

        @Override
        public String getMessage(){
            return "User with userId: " + userId + " not approved.";
        }
}
