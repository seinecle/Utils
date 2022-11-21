/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

open module net.clementlevallois.utils.tests {
// I use junit4
    requires org.junit.jupiter.engine;
    requires org.junit.jupiter.api;
    requires org.junit.platform.engine;
    requires org.junit.platform.commons;
    requires net.clementlevallois.utils;
    requires java.logging;
        requires java.base;

}
