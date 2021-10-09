package com.zhangwy.sample.entity;

import android.net.Uri;

import com.zhangwy.util.StringUtil;

/**
 * CreateTime 2021/8/16 16:07
 * Author zhangwy
 * desc:
 *
 * -------------------------------------------------------------------------------------------------
 * use:
 *
 **/
public class ReadContactEntity extends BaseEntity {
    private String name;
    private Uri uri;

    private ReadContactEntity(Builder builder) {
        setCode(builder.code);
        setMsg(builder.msg);
        name = builder.name;
        uri = builder.uri;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReadContactEntity copy) {
        Builder builder = new Builder();
        builder.code = copy.getCode();
        builder.msg = copy.getMsg();
        builder.name = copy.getName();
        builder.uri = copy.getUri();
        return builder;
    }

    public String getName() {
        return StringUtil.isEmpty(this.name) ? "" : this.name;
    }

    public Uri getUri() {
        return uri;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    /**
     * {@code ReadContactEntity} builder static inner class.
     */
    public static final class Builder {
        private int code;
        private String msg;
        private String name;
        private Uri uri;

        private Builder() {
        }

        /**
         * Sets the {@code code} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param code the {@code code} to set
         * @return a reference to this Builder
         */
        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        /**
         * Sets the {@code msg} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param msg the {@code msg} to set
         * @return a reference to this Builder
         */
        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
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
         * Sets the {@code uri} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param uri the {@code uri} to set
         * @return a reference to this Builder
         */
        public Builder setUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        /**
         * Returns a {@code ReadContactEntity} built from the parameters previously set.
         *
         * @return a {@code ReadContactEntity} built with parameters of this {@code ReadContactEntity.Builder}
         */
        public ReadContactEntity build() {
            return new ReadContactEntity(this);
        }
    }
}
