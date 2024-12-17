package com.example.heatmap;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HeatMapApplication {
    
    public static void main(String[] args) throws IOException {
        double[][] data = new double[][]{
                {3,2,3,4,5,6},
                {2,3,4,5,6,7},
                {3,4,5,6,7,6},
                {4,5,6,7,6,5}};
        
        // Create our heat chart using our data.
        cn.com.jxtele.risk.utils.HeatChart chart = new cn.com.jxtele.risk.utils.HeatChart(data);
        
        // Customise the chart.
        chart.setTitle("管廊燃气");
        chart.setXAxisLabel("管廊高度/m");
        chart.setYValues(-1,2);
        chart.setYAxisLabel("管廊长度/m");
        chart.setHighValueColour(Color.red);
        chart.setLowValueColour(Color.white);
        
        chart.setColourScale(3);
        chart.setCellSize(new Dimension(200,200));
        // Output the chart to a file.
        chart.saveToFile(new File("C:\\Users\\MR.WHO\\Desktop\\my-chart.png"));
    }
    
}
