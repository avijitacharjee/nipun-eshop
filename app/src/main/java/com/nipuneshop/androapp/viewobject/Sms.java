package com.nipuneshop.androapp.viewobject;

import java.util.List;

/**
 * Created by Avijit Acharjee on 8/16/2020 at 10:49 PM.
 * Email: avijitach@gmail.com.
 */
public class Sms {

    /**
     * ErrorCode : 000
     * ErrorMessage : Done
     * JobId : 49256
     * MessageData : [{"Number":"01878362896","MessageId":"ejR5Q3Z5ZXBhd2o1UFF2bEltb0JDZz09"}]
     */

    private String ErrorCode;
    private String ErrorMessage;
    private int JobId;
    private List<MessageDataBean> MessageData;

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String ErrorMessage) {
        this.ErrorMessage = ErrorMessage;
    }

    public int getJobId() {
        return JobId;
    }

    public void setJobId(int JobId) {
        this.JobId = JobId;
    }

    public List<MessageDataBean> getMessageData() {
        return MessageData;
    }

    public void setMessageData(List<MessageDataBean> MessageData) {
        this.MessageData = MessageData;
    }

    public static class MessageDataBean {
        /**
         * Number : 01878362896
         * MessageId : ejR5Q3Z5ZXBhd2o1UFF2bEltb0JDZz09
         */

        private String Number;
        private String MessageId;

        public String getNumber() {
            return Number;
        }

        public void setNumber(String Number) {
            this.Number = Number;
        }

        public String getMessageId() {
            return MessageId;
        }

        public void setMessageId(String MessageId) {
            this.MessageId = MessageId;
        }
    }
}
