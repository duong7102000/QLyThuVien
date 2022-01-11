package Util;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportToCSV {
    public static boolean exportToCSV(JTable tableToExport,
                                      String pathToExportTo) {

        try {

            TableModel model = tableToExport.getModel();
            File csvOutputFile = new File(pathToExportTo);
            FileWriter csv = new FileWriter(csvOutputFile);

            for (int i = 0; i < model.getColumnCount(); i++) {
                csv.write(model.getColumnName(i) + ",");
            }

            csv.write("\n");

            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (model.getValueAt(i, j).toString().equals("")){
                        csv.write(" ,");
                    }
                    else{
                        csv.write(model.getValueAt(i, j).toString() + ",");
                    }
                }
                csv.write("\n");
            }

            csv.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
