/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.couch.view;

import edu.marius.couch.dao.SensorMeasurementDao;
import edu.marius.couch.model.SensorMeasurement;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Marius
 */
@ManagedBean(name = "measurementsViewBean")
@ViewScoped
public class MeasurementsView implements Serializable{
    
    @EJB
    private SensorMeasurementDao measurementsDao;
    
    private List<SensorMeasurement> measurements;
    private LineChartModel lineModel;
    
    @PostConstruct
    public void init(){
        measurements = measurementsDao.getAll();
        measurements.sort(new Comparator<SensorMeasurement>(){
            @Override
            public int compare(SensorMeasurement m1, SensorMeasurement m2) {
                return m2.getTimestamp().compareTo(m1.getTimestamp());
            }            
        });
        createLineModels();
    }
    
    public List<SensorMeasurement> getMeasurements(){
        return this.measurements;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }
    
    private void createLineModels(){
        lineModel = initLineModel();
        lineModel.setTitle("Temperature Graph");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Date"));
        
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Temperature");
        yAxis.setMin(20);
        yAxis.setMax(25);
        
    }
    private LineChartModel initLineModel() {
        LineChartModel model = new LineChartModel();
 
        ChartSeries tempSeries = new ChartSeries();
        tempSeries.setLabel("Temperature");
        tempSeries.set("2004", 52);
        tempSeries.set("2005", 60);
        tempSeries.set("2006", 110);
        tempSeries.set("2007", 90);
        tempSeries.set("2008", 120);
 
        model.addSeries(tempSeries);
         
        return model;
    }
    
}
