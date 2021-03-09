///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.clementlevallois.utils;
//
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.json.JsonWriter;
//import javax.json.JsonWriterFactory;
//import javax.json.stream.JsonGenerator;
//
///**
// *
// * @author LEVALLOIS
// */
//public class JsonUtils {
//
//    public static boolean isAKeyinJsonArray(JsonArray ja, String key) {
//        Set<String> keys = ja.stream().map(x -> x.asJsonObject().keySet()).flatMap(c -> c.stream()).collect(Collectors.toSet());
//        return keys.contains(key);
//    }
//
//    /**
//     * Assumes the elements of the jsonArray are JsonObjects made of a single
//     * key - value pair
//     *
//     * @param jsonArray
//     * @param key. The key of the JsonObject which is the element searched in
//     * the jsonArray.
//     * @return
//     */
//    public static Integer returnIndexOfKeyInJsonArray(JsonArray jsonArray, String key) {
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JsonObject jo = jsonArray.getJsonObject(i);
//            if (jo.containsKey(key)) {
//                return i;
//            }
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param json
//     * @return same json string but prettyfied
//     */
//    public static String prettyPrint(String json) {
//        StringWriter sw = new StringWriter();
//
//        try {
//            JsonReader jr = Json.createReader(new StringReader(json));
//
//            JsonObject jobj = jr.readObject();
//
//            Map<String, Object> properties = new HashMap(1);
//            properties.put(JsonGenerator.PRETTY_PRINTING, true);
//
//            JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
//            JsonWriter jsonWriter = writerFactory.createWriter(sw);
//
//            jsonWriter.writeObject(jobj);
//            jsonWriter.close();
//
//        } catch (Exception e) {
//            System.out.println("Exception:");
//            System.out.println(e.toString());
//        }
//
//        String prettyPrinted = sw.toString();
//
//        return prettyPrinted;
//    }
//
//}
