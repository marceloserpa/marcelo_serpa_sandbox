package com.marceloserpa.fun.builder;

public record Address(String country, String city, String street, String zipCode) {

    private Address(Builder builder) {
        this(builder.country, builder.city, builder.street, builder.zipCode);
    }


    public static class Builder {

        private String country;
        private String city;
        private String street;
        private String zipCode;

        public Builder country(String country){
            this.country = country;
            return this;
        }

        public Builder city(String city){
            this.city = city;
            return this;
        }

        public Builder street(String street){
            this.street = street;
            return this;
        }

        public Builder zipCode(String zipCode){
            this.zipCode = zipCode;
            return this;
        }

        public Address build(){
            return new Address(this);
        }

    }
}
