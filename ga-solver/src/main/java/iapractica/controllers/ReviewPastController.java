/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.controllers;

import genetics.cromosomas.external.ExternalDataHandler;
import genetics.individuos.Individuo;
import genetics.individuos.IndividuosFactory;
import iapractica.drawers.DataDrawer;
import iapractica.views.MainPanelView;
import iapractica.views.ReviewPastTestsView;
import iapractica.views.popups.PopUpFactory;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 *
 * @author Justo Vargas
 */
public class ReviewPastController extends GenericController {

    private final ExternalDataHandler dataManager;
    private LinkedList<Individuo> currentPopulation;
    private int currentAge;
    private LinkedList<Integer> materiasPrimas;

    public ReviewPastController() {
        super(new ReviewPastTestsView());
        dataManager = new ExternalDataHandler();
    }

    @Override
    public void init() {
    }

    @Override
    public void show() {
        this.view.reset();
        boolean existData = false;
        try {
            existData = dataManager.existBeforeData();
            if (existData) {
                int getTotalElements = dataManager.getTotalElements();
                if (getTotalElements == 0) {
                    PopUpFactory.showConfirmPopUP(view, "No hiciste ninguna corrida Anteriormente o Se Borraron los datos");
                } else {
                    ReviewPastTestsView panel = (ReviewPastTestsView) view;
                    panel.setData(getTotalElements);
                    this.view.setVisible(true);
                }
            } else {
                PopUpFactory.showConfirmPopUP(view, "No hiciste ninguna corrida Anteriormente");
            }
        }
        catch (FileNotFoundException e) {
            PopUpFactory.showConfirmPopUP(view, "No hiciste ninguna corrida Anteriormente");
        }

    }

    @Override
    public void dispose() {
        active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void notifySelection(int currentSelection) {
        this.currentAge = currentSelection;
        currentPopulation = dataManager.getFromExternalFile(currentAge);
        this.materiasPrimas = dataManager.getFromMateriasPrimasExternalFile();
        IndividuosFactory.getInstance().setMateriasPrimas(materiasPrimas);
        ReviewPastTestsView panel = (ReviewPastTestsView) view;
        panel.populateDataModel(currentPopulation, materiasPrimas);
    }
}
