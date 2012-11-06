/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.controllers;

import genetics.functions.managers.CruzaManager.Cruzators;
import genetics.functions.managers.MutatorManager.Mutators;
import genetics.functions.managers.SelectionManager.Selectors;
import genetics.individuos.Individuo;
import genetics.individuos.Poblacion;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import iapractica.drawers.DataDrawer;
import iapractica.views.MainPanelView;
import iapractica.views.FinalResultsView;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class MainPanelController extends GenericController {
    
    private Poblacion population;
    private LinkedList<Integer> materiasPrimas = new LinkedList<Integer>();
    private int maximumPopulation;
    private int maximumAge;
    private HashMap<Cruzators, Integer> cruzaCoverageMethods;
    private HashMap<Selectors, Integer> selectionCoverageMethods;
    private HashMap<Mutators, Integer> mutationsCoverageMethods;
    private int selectionManagerPercentage;
    private int cruzaManagerPercentage;
    private int mutatorManagerPercentage;
    private FinalResultsView theFinalView;
    
    public MainPanelController() {
        super(new MainPanelView());
    }
    
    @Override
    public void init() {
        
    }
    
    @Override
    public void show() {
        this.view.reset();
        this.view.setVisible(true);
    }
    
    public void startSimulation() throws NoMateriaPrimaAddedException {
        
        if (materiasPrimas.isEmpty()) {
            throw new NoMateriaPrimaAddedException("No Materia was added");
        }
        population = new Poblacion(this, materiasPrimas, maximumAge,maximumPopulation);
        population.setGAOperators(selectionManagerPercentage, mutatorManagerPercentage, cruzaManagerPercentage, mutationsCoverageMethods, selectionCoverageMethods, cruzaCoverageMethods);
        population.start();
    }
    
    public void pauseSimulation(boolean value) {
        population.pause(value);
    }
    
    public void getBackOneAge() {
        population.rewind();
    }
    
    public void forwardOneAge() {
        population.forward();
    }
    
    public void destroyCurrentSimulation() {
        if (population != null) {
            this.population.setRunning(false);
            this.population.interrupt();
        }
        this.population = null;
    }
    
    @Override
    public void dispose() {
        destroyCurrentSimulation();
        active = false;
    }
    
    public void addMateriaPrima(int[] materiasPrimas) {
        int size = materiasPrimas.length;
        this.materiasPrimas.clear();
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

    public void updateChart(int age, LinkedList<Individuo> currentPopulation) {
        MainPanelView panel = (MainPanelView) this.view;
        panel.updateChart(age, currentPopulation);
    }

    public void setGAOperators(int selectionPercentage, int mutatorPercentage, int cruzaPercentage, HashMap<Mutators, Integer> mutationsCoverageMethods, HashMap<Selectors, Integer> selectionCoverageMethods, HashMap<Cruzators, Integer> cruzaCoverageMethods) {
        this.mutatorManagerPercentage=mutatorPercentage;
        this.selectionManagerPercentage=selectionPercentage;
        this.cruzaManagerPercentage=cruzaPercentage;
        
        this.cruzaCoverageMethods = cruzaCoverageMethods;
        this.selectionCoverageMethods = selectionCoverageMethods;
        this.mutationsCoverageMethods = mutationsCoverageMethods;
    }

    public void showLastScreen(LinkedList<Individuo> currentPopulation) {
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException ex) {
           
        }
        MainPanelView panel = (MainPanelView) view;
        if (view != null && view.isVisible()){
            panel.saveImages();
            panel.enablePlayButton(false);
            theFinalView = new FinalResultsView(this, currentPopulation, (LinkedList<Integer>)materiasPrimas.clone());
            theFinalView.setVisible(true);
        }
    }

    public void notifyClosed() {
        MainPanelView panel = (MainPanelView) view;
        panel.enablePlayButton(true);
    }

    @Override
    public boolean isActive() {
        return active;
    }

   

}
