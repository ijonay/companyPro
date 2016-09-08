package com.zc.service;

import java.io.File;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.ManhattanDistance;
import weka.core.converters.ArffLoader;

public class Test {
    public static void test(File file) throws Exception{
        ArffLoader loader = new ArffLoader();  
        loader.setFile(file);  
        Instances ins = loader.getDataSet();
        
        ManhattanDistance manhattan = new ManhattanDistance();
        
        SimpleKMeans km=new SimpleKMeans();
        km.setNumClusters(10);
        km.setPreserveInstancesOrder(true);
        km.setDistanceFunction(manhattan);
        km.buildClusterer(ins);
        
       
        Instances centers=km.getClusterCentroids();
        for ( int i = 0; i < centers.numInstances(); i++ ) {
            // for each cluster center
            Instance inst = centers.instance( i );
            // as you mentioned, you only had 1 attribute
            // but you can iterate through the different attributes
            double value = inst.value( 0 );
            System.out.println( "Value for centroid " + i + ": " + value );
        }
        
        km.getSquaredError();
        
    }
    
    public static void main(String[] args) throws Exception{
        File file=new File("C:\\Users\\huyulinhome\\Desktop\\1.txt");
        test(file);
    }
}
