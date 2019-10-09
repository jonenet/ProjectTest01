package com.example.builder;

/**
 * Desc: TODO
 * <p>
 * Author: meijie
 * PackageName: com.example.builder
 * ProjectName: ProjectTest01
 * Date: 2019/7/18 15:40
 */
public class OpenConfig {

    private long id;
    private String name;
    private int age;
    private long timeStamp;

    public static Builder newBuild() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    private OpenConfig(Builder builder) {
        id = builder.id;
        name = builder.name == null ? "" : builder.name;
        age = builder.age;
        timeStamp = builder.timeStamp;
    }

    static class Builder {
        private long id;
        private String name;
        private int age;
        private long timeStamp;


        public Builder setId(long id) {
            this.id = id;
            return this;
        }


        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public Builder setAge(int age) {
            this.age = age;
            return this;
        }


        public Builder setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }


        //
        public OpenConfig build() {
            return new OpenConfig(this);
        }
    }

}
