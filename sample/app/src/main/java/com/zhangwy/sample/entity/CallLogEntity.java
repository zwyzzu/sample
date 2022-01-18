package com.zhangwy.sample.entity;

import android.provider.CallLog;

/**
 * CreateTime 2021/10/10 13:01
 * Author zhangwy
 * desc:
 * <p>
 * -------------------------------------------------------------------------------------------------
 * use:
 **/
public class CallLogEntity extends BaseEntity {
    private String name;
    private String number;
    private long dateLong;
    private int duration;
    private int type;

    private CallLogEntity(Builder builder) {
        name = builder.name;
        number = builder.number;
        dateLong = builder.dateLong;
        duration = builder.duration;
        type = builder.type;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(CallLogEntity copy) {
        Builder builder = new Builder();
        builder.name = copy.getName();
        builder.number = copy.getNumber();
        builder.dateLong = copy.getDateLong();
        builder.duration = copy.getDuration();
        builder.type = copy.getType();
        return builder;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public long getDateLong() {
        return dateLong;
    }

    public int getDuration() {
        return duration;
    }

    public int getType() {
        return type;
    }

    /**
     * {@code CallLogEntity} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private String number;
        private long dateLong;
        private int duration;
        private int type;

        private Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code number} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param number the {@code number} to set
         * @return a reference to this Builder
         */
        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        /**
         * Sets the {@code dateLong} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dateLong the {@code dateLong} to set
         * @return a reference to this Builder
         */
        public Builder setDateLong(long dateLong) {
            this.dateLong = dateLong;
            return this;
        }

        /**
         * Sets the {@code duration} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param duration the {@code duration} to set
         * @return a reference to this Builder
         */
        public Builder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        /**
         * Sets the {@code type} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param type the {@code type} to set
         * @return a reference to this Builder
         */
        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        /**
         * Returns a {@code CallLogEntity} built from the parameters previously set.
         *
         * @return a {@code CallLogEntity} built with parameters of this {@code CallLogEntity.Builder}
         */
        public CallLogEntity build() {
            return new CallLogEntity(this);
        }
    }

    public enum CallType {
        UNKNOWN(-99, "未知类型"),
        INCOMING(CallLog.Calls.INCOMING_TYPE, "拨入"),
        OUTGOING(CallLog.Calls.OUTGOING_TYPE, "播出"),
        MISSED(CallLog.Calls.MISSED_TYPE, "未接听"),
        REJECTED(CallLog.Calls.REJECTED_TYPE, "拒绝接听"),
        VOICEMAIL(CallLog.Calls.VOICEMAIL_TYPE, "语音邮件的呼叫"),
        BLOCKED(CallLog.Calls.BLOCKED_TYPE, "自动阻塞呼叫"),
        ANSWERED_EXTERNALLY(CallLog.Calls.ANSWERED_EXTERNALLY_TYPE, "呼叫转移");

        private int code;
        private String desc;

        CallType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        public CallType find(int code) {
            for (CallType type : CallType.values()) {
                if (type.code == code) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }
}
