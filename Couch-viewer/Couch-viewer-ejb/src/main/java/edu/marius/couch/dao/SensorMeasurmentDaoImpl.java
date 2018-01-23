/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.marius.couch.dao;

import edu.marius.couch.model.SensorMeasurement;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

/**
 *
 * @author Marius
 */
@Stateless
public class SensorMeasurmentDaoImpl implements SensorMeasurementDao, Serializable{

    private HttpClient httpClient;
    private CouchDbInstance dbInstance;
    private CouchDbConnector db;
    private SensorMeasurementRepository repo;
    
    public SensorMeasurmentDaoImpl(){
        try {
            httpClient = new StdHttpClient.Builder()
                .url("http://localhost:5984")
                .build();            
            dbInstance = new StdCouchDbInstance(httpClient);
            db = new StdCouchDbConnector("sensor_db", dbInstance);
            repo = new SensorMeasurementRepository(db);
        } catch (MalformedURLException ex) {
            Logger.getLogger(SensorMeasurmentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<SensorMeasurement> getAll() {
        return repo.getAll();
    }

    
    
}
