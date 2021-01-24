package com.ibar.demo.config;


public class Serializer {
//    public static RedisSerializer<Long> createRedisSerializerForLongClass() {
//        return new RedisSerializer<Long>() {
//            @Override
//            public byte[] serialize(Long aLong) throws SerializationException {
//                return aLong.toString().getBytes();
//            }
//
//            @Override
//            public Long deserialize(byte[] bytes) throws SerializationException {
//                return Long.parseLong(new String(bytes));
//            }
//        };
//
//    }
//
//    public static RedisSerializer<User> createRedisSerializerForUserClass() {
//        return new RedisSerializer<User>() {
//            @Override
//            public byte[] serialize(User user) throws SerializationException {
//                try {
//                    return new ObjectMapper().writeValueAsBytes(user);
//                } catch (JsonProcessingException ex) {
//                    return null;
//                }
//
//            }
//
//            @Override
//            public User deserialize(byte[] bytes) throws SerializationException {
//                try {
//                    return new ObjectMapper().readValue(new String(bytes), User.class);
//                } catch (JsonProcessingException ex) {
//                    return null;
//                }
//            }
//        };
//    }
//
//    public static RedisSerializer<OTP> createRedisSerializerForOtpClass() {
//        return new RedisSerializer<OTP>() {
//            @Override
//            public byte[] serialize(OTP otp) throws SerializationException {
//                try {
//                    return new ObjectMapper().writeValueAsBytes(otp);
//                } catch (JsonProcessingException ex) {
//                    return null;
//                }
//
//            }
//
//            @Override
//            public OTP deserialize(byte[] bytes) throws SerializationException {
//                try {
//                    return new ObjectMapper().readValue(new String(bytes), OTP.class);
//                } catch (JsonProcessingException ex) {
//                    return null;
//                }
//            }
//        };
//    }
}
