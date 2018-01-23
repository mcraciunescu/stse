/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.couch.dao;

import edu.marius.couch.model.SensorMeasurement;
import java.util.List;

/**
 *
 * @author Marius
 */
public interface SensorMeasurementDao {
    public List<SensorMeasurement> getAll();
}
