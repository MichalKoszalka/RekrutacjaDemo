/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.stooqwebloader.utils;

import com.example.stooqwebloader.domain.Index;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Michał
 */
@Stateless
public class ContentParser {

    public DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public List<Index> parseContentToObject(String content) {
        String[] lines = content.split("\\r?\\n");
        List<String> wigs = new ArrayList<>();
        List<Index> indexes = new ArrayList<>();
        for (String stringLine : lines) {
            if (stringLine.contains("WIG,") || stringLine.contains("WIG20,")
                || stringLine.contains("WIG20FUT,") || stringLine.contains("MWIG40,")
                || stringLine.contains("SWIG80,")) {
                wigs.add(stringLine);
            }
        }
        for (String line : wigs) {
            indexes.add(parseToIndex(line));
        }
        return new ArrayList<>(filterIndexes(indexes));
    }

    private Index parseToIndex(String line) {
        Index index = new Index();
        String[] data = line.split(",");
        index.setId(data[0]);
        try {
            Date dateAndTime = dateFormat.parse(data[2] + data[3]);
            index.setDateAndTime(dateAndTime);
        } catch (ParseException ex) {
            Logger.getLogger(ContentParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        index.setPrice(Double.parseDouble(data[4]));
        return index;
    }

    private static List<Index> filterIndexes(List<Index> source) {
        List<Index> toRemove = new ArrayList<>();
        for (Index index : source) {
            for (Index secondIndex : source) {
                if (index != secondIndex && index.getId().equals(secondIndex.getId())) {
                    if (index.getDateAndTime().before(secondIndex.getDateAndTime())) {
                        toRemove.add(index);
                    } else {
                        toRemove.add(secondIndex);
                    }
                }
            }
        }
        for(Index index : toRemove) {
            source.remove(index);
        }
        return source; 
    }

}
