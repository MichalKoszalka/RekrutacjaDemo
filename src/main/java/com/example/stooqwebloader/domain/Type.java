/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stooqwebloader.domain;

/**
 *
 * @author Michał
 */
public enum Type {
    WIG {
        @Override
        public String toString() {
            return "WIG";
        }
    },
    WIG20 {
        @Override
        public String toString() {
            return "WIG20";
        }
    },
    WIG20FUT {
        @Override
        public String toString() {
            return "WIG20FUT";
        }
    },
    MWIG40 {
        @Override
        public String toString() {
            return "MWIG40";
        }
    },
    SWIG80 {
        @Override
        public String toString() {
            return "SWIG80";
        }
    },
}