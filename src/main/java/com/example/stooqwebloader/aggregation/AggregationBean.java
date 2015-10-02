package com.example.stooqwebloader.aggregation;

import com.example.stooqwebloader.domain.Index;
import com.example.stooqwebloader.utils.ContentParser;
import com.example.stooqwebloader.utils.IndexesFIleWriter;
import com.example.stooqwebloader.utils.ResponseLoader;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author Michał
 */
@Stateless
@Singleton
public class AggregationBean {

    @EJB
    private ContentParser contentParser;

    @EJB
    private ResponseLoader responseLoader;

    @EJB
    private IndexesFIleWriter fileWriter;

    private static final String URL = "http://stooq.pl/db/d/?d=20150930&t=5";

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void aggregateIndexes() {
        System.out.println("aggregation performing");
        List<Index> indexes = contentParser.parseContentToObject(responseLoader.loadResponse(URL));
        fileWriter.writeToFile(indexes);
    }

}
