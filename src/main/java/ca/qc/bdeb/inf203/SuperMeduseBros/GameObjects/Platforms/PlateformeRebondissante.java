package ca.qc.bdeb.inf203.SuperMeduseBros.GameObjects.Platforms;

import ca.qc.bdeb.inf203.SuperMeduseBros.Partie;
import javafx.scene.paint.Color;

/**
 * Plate-forme rebondissante (vert p�le)
 * La plate-forme rebondissante a pour effet de faire faire un rebond � la m�duse lorsqu�elle tombe
 * dessus.
 * Lorsque la m�duse entre en collision avec la plate-forme (donc en tombant dessus depuis le haut), sa
 * vitesse verticale vy est invers�e comme dans tous les rebonds, mais elle est �galement amplifi�e par
 * un facteur de �1.5.
 * Pour assurer un rebond minimalement int�ressant, la vitesse apr�s rebond est forc�e � �tre au moins
 * de 100px/s vers le haut.
 * Utilisez la couleur Color.LIGHTGREEN pour afficher ces plates-formes.
 */
public class PlateformeRebondissante extends Plateforme {

    public static final Color COULEUR = Color.LIGHTGREEN;

    public PlateformeRebondissante(double x, double y, double width, Partie partie) {
        super(x, y, width, COULEUR, partie);
    }

    @Override
    public void landOn(){
        final double minVy = -100; // negatif donc vers le haut
        double newVy = -partie.getMeduse().vy * 1.5;

        partie.getMeduse().vy = Math.min(newVy, minVy);
    }
}
