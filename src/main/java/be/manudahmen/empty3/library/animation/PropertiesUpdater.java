/**
 * *
 * Global license : * CC Attribution
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package be.manudahmen.empty3.library.animation;

/**
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public interface PropertiesUpdater {

    void addMouvements(AnimationMouvements mvt);

    Model modele();

    void updateModel();

    void updatePropeties();
}
