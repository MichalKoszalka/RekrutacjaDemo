package com.example.stooqwebloader.utils;

import com.example.stooqwebloader.domain.Index;
import com.example.stooqwebloader.domain.Type;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author Michał
 */
@Stateless
@Singleton
public class IndexesFIleWriter {

    private Double lastWIG;
    private Double lastWIG20;
    private Double lastWIG20FUT;
    private Double lastMWIG40;
    private Double lastSWIG80;

    private FileWriter writer;

    public void writeToFile(List<Index> indexes) {
        File file = new File("indexes.txt");

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            writer = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            
            for (Index index : updateWigs(indexes)) {
                bufferedWriter.write(index.toString());
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ContentParser.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private List<Index> updateWigs(List<Index> indexes) {
        List<Index> indexesToWrite = new ArrayList<>();
        for (Index index : indexes) {
            if (index.getId().equals(Type.WIG.toString())) {
                if (lastWIG == null || !Objects.equals(lastWIG, index.getPrice())) {
                    lastWIG = index.getPrice();
                    indexesToWrite.add(index);
                }
            }
            if (index.getId().equals(Type.WIG20.toString())) {
                if (lastWIG20 == null || !Objects.equals(lastWIG20, index.getPrice())) {
                    lastWIG20 = index.getPrice();
                    indexesToWrite.add(index);
                }
            }
            if (index.getId().equals(Type.MWIG40.toString())) {
                if (lastMWIG40 == null || !Objects.equals(lastMWIG40, index.getPrice())) {
                    lastMWIG40 = index.getPrice();
                    indexesToWrite.add(index);
                }
            }
            if (index.getId().equals(Type.SWIG80.toString())) {
                if (lastSWIG80 == null || !Objects.equals(lastSWIG80, index.getPrice())) {
                    lastSWIG80 = index.getPrice();
                    indexesToWrite.add(index);
                }
            }
            if (index.getId().equals(Type.WIG20FUT.toString())) {
                if (lastWIG20FUT == null || !Objects.equals(lastWIG20FUT, index.getPrice())) {
                    lastWIG20FUT = index.getPrice();
                    indexesToWrite.add(index);
                }
            }
        }
        return indexesToWrite;
    }

}
