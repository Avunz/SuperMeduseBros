package ca.qc.bdeb.inf203.SuperMeduseBros;

import ca.qc.bdeb.inf203.SuperMeduseBros.GameObjects.GameObject;
import ca.qc.bdeb.inf203.SuperMeduseBros.GameObjects.Meduse;
import ca.qc.bdeb.inf203.SuperMeduseBros.GameObjects.Platforms.Plateforme;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class Partie {

    private final double gameWidth, gameHeight;
    private Meduse meduse;
    private Camera camera;
    private PlateformeManager platManager;

    Set<GameObject> gameObjects = new HashSet<>();

    public Partie(double gameWidth, double gameHeight){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        //start the game
        start();
    }

    private void start(){
        //create the camera
        camera = new Camera(this);

        //create the plateformes
        platManager = new PlateformeManager(this);
        platManager.start();

        //create the m�duse
        Plateforme spawnPlat = platManager.getPlateformes().get((int)Math.ceil(platManager.getPlateformes().size()/2d - 1));
        meduse = new Meduse(
                spawnPlat.getGauche() + spawnPlat.getWidth()/2 - Meduse.WIDTH/2,
                spawnPlat.getHaut() - Meduse.HEIGHT - 35, // -35 pour �viter que la m�duse ne sorte de la plateforme
                this
        );

        //add the gameObjects
        gameObjects.add(meduse);
    }

    private void restart(){
        //tmp impl�mentation
        System.out.println("restart");
        gameObjects.clear();
        start();
    }

    public void update(double deltaTemps){

        //update camera
        camera.update(deltaTemps);


        //update gameObjects
        for (GameObject gameObject : gameObjects) {
            gameObject.update(deltaTemps);
        }


    }

    public void draw(GraphicsContext context, long now){
        context.clearRect(0, 0, gameWidth, gameHeight);
        context.setFill(Color.BLUE);
        context.fillRect(0, 0, gameWidth, gameHeight);

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(context, now);
        }
    }

    public Meduse getMeduse() {
        return meduse;
    }

    public double getGameWidth() {
        return gameWidth;
    }

    public double getGameHeight() {
        return gameHeight;
    }

    public Camera getCamera() {
        return camera;
    }

    /**
     * Cette m�thode est appel�e par la camera quand la m�duse est en dessous de la camera (on a donc perdu)
     */
    public void defaite() {
        //tmp impl�mentation
        System.out.println("perdu");
        restart();
    }

    public void removeGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public PlateformeManager getPlatManager() {
        return platManager;
    }
}
