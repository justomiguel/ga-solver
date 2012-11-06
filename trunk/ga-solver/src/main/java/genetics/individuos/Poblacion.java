package genetics.individuos;

import com.frre.cemami.utils.DefaultLogguer;
import genetics.cromosomas.external.ExternalDataHandler;
import genetics.functions.managers.CruzaManager;
import genetics.functions.managers.CruzaManager.Cruzators;
import genetics.functions.managers.MutatorManager;
import genetics.functions.managers.MutatorManager.Mutators;
import genetics.functions.managers.SelectionManager;
import genetics.functions.managers.SelectionManager.Selectors;
import genetics.productos.exceptions.NoMateriaPrimaAddedException;
import iapractica.controllers.GenericController;
import iapractica.controllers.MainPanelController;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public final class Poblacion extends Thread {

    static DefaultLogguer logguer = DefaultLogguer.getLogger();
    private static final int MAXIMUM_DEFAULT_AGE = 600;
    private static final int MAXIMUM_DEFAULT_POPULATION_SIZE = 100;
    //container for the population while it is in memory
    private LinkedList<Individuo> currentPopulation;
    //control the population
    private int maximumAge;
    private int maximumPopulation;
    //curent age
    private int age;
    //says if the thread is running
    private boolean running;
    //says if the thread is running
    private boolean inPause;
    //Selector manager
    private SelectionManager selectionManager;
    //Cruzas manager
    private CruzaManager cruzaManager;
    //Cruzas manager
    private MutatorManager mutatorManager;
    //
    private HashMap<Selectors, Integer> selectionCoverageMethods;
    private HashMap<Cruzators, Integer> cruzaCoverageMethods;
    private HashMap<Mutators, Integer> mutationsCoverageMethods;
    //external data hanlder
    private ExternalDataHandler dataManager;
    private MainPanelController controller;
    //materias primas
    private LinkedList<Integer> materiasPrimas;
    private int sleepTime = 0;

    public Poblacion(GenericController controller, LinkedList<Integer> materiasPrimas) {
        maximumAge = MAXIMUM_DEFAULT_AGE;
        maximumPopulation = MAXIMUM_DEFAULT_POPULATION_SIZE;
        this.controller = (MainPanelController) controller;
        this.materiasPrimas = materiasPrimas;
        init();
    }

    public Poblacion(GenericController controller, LinkedList<Integer> materiasPrimas, int maxAge, int maxPopulation) {
        maximumAge = maxAge;
        maximumPopulation = maxPopulation;
        this.controller = (MainPanelController) controller;
        this.materiasPrimas = materiasPrimas;
        init();
    }

    private void init() {
        age = 1;
        //my population
        currentPopulation = new LinkedList<Individuo>();
        //the managers
        selectionManager = new SelectionManager();
        cruzaManager = new CruzaManager();
        mutatorManager = new MutatorManager();
        //the methods
        //setting selectors
        selectionCoverageMethods = new HashMap<Selectors, Integer>();
        //setting cruzators
        cruzaCoverageMethods = new HashMap<Cruzators, Integer>();
        //setting mutators
        mutationsCoverageMethods = new HashMap<Mutators, Integer>();

        dataManager = new ExternalDataHandler();
    }

    public void evolve() {
        //do stuff
        LinkedList<Individuo> newPopulationAfterSelection = selectionManager.doSelection(currentPopulation, selectionCoverageMethods);
        LinkedList<Individuo> newPopulationAfterCruzas = cruzaManager.doCruzas(currentPopulation, cruzaCoverageMethods);
        LinkedList<Individuo> newPopulationAfterMutations = mutatorManager.doMutation(currentPopulation, mutationsCoverageMethods);
        //destroy the preivous population
        currentPopulation.clear();
        currentPopulation.addAll(newPopulationAfterCruzas);
        currentPopulation.addAll(newPopulationAfterSelection);
        currentPopulation.addAll(newPopulationAfterMutations);
        //increment the age
        age++;
    }

    @Override
    public void run() {
        this.updateUIProgress(5);
        try {
            currentPopulation = PoblacionFactory.getInstance().createInitialRandomPopulation(this, maximumPopulation, materiasPrimas);
        }
        catch (NoMateriaPrimaAddedException ex) {
            logguer.logError(this, ex.getMessage(), ex);
        }

        setRunning(true);

        logguer.logInfo(" Starting Simulation ");
        logguer.logInfo("Max Age " + maximumAge);
        logguer.logInfo("CurrentPopulation " + maximumPopulation);

        updateUIChart(age, currentPopulation);

        this.updateUIProgress(50);

        dataManager.deleteOldElements();
        dataManager.saveToMateriasPrimasExternalFile(materiasPrimas);
        
        while (running && age < maximumAge) {
            if (!inPause) {
                processAge();
                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    }
                    catch (InterruptedException ex) {
                        logguer.logError(this, ex.getMessage(), ex);
                    }
                }
            } else {
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException ex) {
                    logguer.logError(this, ex.getMessage(), ex);
                }
            }
        }
        
        running = false;

        //save the last population
        dataManager.saveToExternalFile(age, currentPopulation);
        
        this.updateUIProgress(100);
        
        this.showLastScreen();
    }

    public int getAge() {
        return age;
    }

    public LinkedList<Individuo> getCurrentPopulation() {
        return currentPopulation;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(int maximumAge) {
        this.maximumAge = maximumAge;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isInPause() {
        return inPause;
    }

    public void setInPause(boolean inPause) {
        this.inPause = inPause;
    }

    public void setCoverageMethod() {
    }

    private void updateUIChart(int age, LinkedList<Individuo> currentPopulation) {
        LinkedList<Individuo> cloneIndividuos = PoblacionFactory.getInstance().cloneInitialPopulation(currentPopulation);
        this.controller.updateChart(age, cloneIndividuos);
    }

    public void updateUIProgress(int progress) {
        this.controller.updateProgress(progress);
    }

    @Override
    public void interrupt() {
        super.interrupt();
        //dispose elements here
    }

    public LinkedList<Integer> getMateriasPrimas() {
        return materiasPrimas;
    }

    public void setMateriasPrimas(LinkedList<Integer> materiasPrimas) {
        this.materiasPrimas = materiasPrimas;
    }

    public void pause(boolean value) {
        this.setInPause(value);
    }

    public void rewind() {
        if (age>1){
            age--;
            currentPopulation = dataManager.getFromExternalFile(age);
            processAge();
            age--;
            
        }
    }

    public int getMaximumPopulation() {
        return maximumPopulation;
    }

    public void setMaximumPopulation(int maximumPopulation) {
        this.maximumPopulation = maximumPopulation;
    }

    public void setSimulationVelocity(int value) {
        this.sleepTime = value * 10;
    }

    public void forward() {
        processAge();
    }

    public void setGAOperators(int selectionPercentage, int mutatorPercentage, int cruzaPercentage, HashMap<Mutators, Integer> mutationsCoverageMethods, HashMap<Selectors, Integer> selectionCoverageMethods, HashMap<Cruzators, Integer> cruzaCoverageMethods) {
        this.mutatorManager.setPercentage(mutatorPercentage);
        this.selectionManager.setPercentage(selectionPercentage);
        this.cruzaManager.setPercentage(cruzaPercentage);

        this.cruzaCoverageMethods = cruzaCoverageMethods;
        this.selectionCoverageMethods = selectionCoverageMethods;
        this.mutationsCoverageMethods = mutationsCoverageMethods;
    }

    private void processAge() {
        dataManager.saveToExternalFile(age, currentPopulation);
        evolve();
        updateUIChart(age, currentPopulation);

        //update The progress bar
        int percetageOfSucces = age * 100 / maximumAge;
        this.updateUIProgress(50 + percetageOfSucces * 50 / 100);
    }

    private void showLastScreen() {
        //first 5
        Collections.sort(currentPopulation);
        this.controller.showLastScreen(currentPopulation);
    }
}
