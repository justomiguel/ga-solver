/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.controllers;

import genetics.individuos.Poblacion;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import iapractica.drawers.DataDrawer;
import iapractica.views.MainPanelView;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class MainPanelController extends GenericController {
    
    private Poblacion population;
    private DataDrawer drawer;
    private LinkedList<Integer> materiasPrimas = new LinkedList<Integer>();
    private int maximumPopulation;
    private int maximumAge;
    
    public MainPanelController() {
        super(new MainPanelView());
    }
    
    @Override
    public void init() {
        
    }
    
    @Override
    public void show() {
        MainPanelView panel = (MainPanelView) view;
        this.view.reset();
        drawer = new DataDrawer(panel);
        this.view.setVisible(true);
        
    }
    
    public void startSimulation() throws NoMateriaPrimaAddedException {
        
        if (materiasPrimas.isEmpty()) {
            throw new NoMateriaPrimaAddedException("No Materia was added");
        }
        population = new Poblacion(this, materiasPrimas, maximumAge,maximumPopulation);
        population.start();
    }
    
    public void pauseSimulation(boolean value) {
        population.pause(value);
    }
    
    public void getBackOneAge() {
        population.rewind(1);
    }
    
    public void forwardOneAge() {
        population.forward(1);
    }
    
    public void stopSimulation() {
        population.rewind(-1);
    }
    
    public void destroyCurrentSimulation() {
        if (population != null) {
            this.population.setRunning(false);
            this.population.interrupt();
        }
    }
    
    @Override
    public void dispose() {
        destroyCurrentSimulation();
    }
    
    public void updateChart(LinkedList<Double> newElements, int age, double average) {
        MainPanelView panel = (MainPanelView) this.view;
        panel.updateChart(average, age, newElements);
    }
    
    public void addMateriaPrima(int[] materiasPrimas) {
        int size = materiasPrimas.length;
        for (int i = 0; i < size; i++) {
            this.materiasPrimas.add(materiasPrimas[i]);
        }
    }
    
    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }
    
    public void setMaximumPopulation(int maximumPopulation) {
        this.maximumPopulation = maximumPopulation;
    }
    
    public void setSimulationVelocity(final int value) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                population.setSimulationVelocity(value);
            }
        });
    }
    
    public void updateProgress(int progress) {
        ((MainPanelView) view).updateProgress(progress);
    }
    
    public boolean threadIsRunning() {
        return population != null && population.isRunning();
    }

}
