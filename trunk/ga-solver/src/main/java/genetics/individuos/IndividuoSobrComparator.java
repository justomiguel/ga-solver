/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics.individuos;

import java.util.Comparator;

/**
 *
 * @author developer
 */
public class IndividuoSobrComparator implements Comparator {

    protected boolean isSortAsc;

    public IndividuoSobrComparator(boolean sortAsc) {
        isSortAsc = sortAsc;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (!( o1 instanceof Individuo ) || !( o2 instanceof Individuo )) {
            return 0;
        }
        Integer s1 = (Integer) ( (Individuo) o1 ).getMateriaPrimaSobrante();
        Integer s2 = (Integer) ( (Individuo) o2 ).getMateriaPrimaSobrante();
        int result;
        result = s1.compareTo(s2);
        if (!isSortAsc) {
            result = -result;
        }
        return result;
    }
    /*

     public boolean equals(Object obj) {
     if (obj instanceof MyComparator) {
     MyComparator compObj = (MyComparator) obj;
     return compObj.isSortAsc == isSortAsc;
     }
     return false;
     }*/
}