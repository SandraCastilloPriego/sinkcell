/*
 * Copyright 2008-2010 The sinkCell Development Team
 *
 * This file is part of sinkCell.
 *
 * sinkCell is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * sinkCell is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * sinkCell; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package sinkjava;

import com.csvreader.CsvWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandra
 */
public class ExportCSV {

    private CsvWriter w;

    public ExportCSV() {
        Date now = new Date();
        String fileName = new java.io.File("").getAbsolutePath() + "/Sink_SimulationFile_" + now.toString() + ".csv";
        w = new CsvWriter(fileName);
    }

    public void exportParameters(Parameters parameters) {
        try {
            String[] data = new String[4];
            data[0] = "General Parameters";
            w.writeRecord(data);
            data[0] = "Population = ";
            data[1] = String.valueOf(parameters.population);
            data[2] = "Mortality = ";
            data[3] = String.valueOf(parameters.mortality);
            w.writeRecord(data);
            data[0] = "Patch = ";
            data[1] = String.valueOf(parameters.patch);
            data[2] = "Size = ";
            data[3] = String.valueOf(parameters.size);
            w.writeRecord(data);

            data = new String[4];
            data[0] = "Internal Variables";
            w.writeRecord(data);
            data[0] = "Var 1 = ";
            data[1] = String.valueOf(parameters.var1);
            data[2] = "Var 2 = ";
            data[3] = String.valueOf(parameters.var2);
            w.writeRecord(data);

            data[0] = "Var 3 = ";
            data[1] = String.valueOf(parameters.var3);
            data[2] = "Var 4 = ";
            data[3] = String.valueOf(parameters.var4);
            w.writeRecord(data);


            // export head
            data = new String[4];
            data[0] = "Iteration number";
            data[1] = "Population";
            data[2] = "Density";
            data[3] = "Proximity";
            w.writeRecord(data);

        } catch (IOException ex) {
            Logger.getLogger(ExportCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportData(int iteration, int population, float density, float proximity) {
        try {
            String[] data = new String[4];
            data[0] = String.valueOf(iteration);
            data[1] = String.valueOf(population);
            data[2] = String.valueOf(density);
            data[3] = String.valueOf(proximity);
            w.writeRecord(data);
        } catch (IOException ex) {
            Logger.getLogger(ExportCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeFile() {
        try {
            if (w != null) {
                w.endRecord();
                w.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ExportCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
