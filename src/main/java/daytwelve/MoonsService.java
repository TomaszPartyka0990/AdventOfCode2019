package daytwelve;

import java.util.ArrayList;
import java.util.List;

public class MoonsService {
    List<Moon> moons;
    List<Moon> initialState;

    public MoonsService(List<Moon> moons) {
        this.moons = moons;
        initialState = new ArrayList<>();
        for (Moon moon:moons) {
            Moon tmpMoon = new Moon(moon.getxPosition(), moon.getyPosition(), moon.getzPosition());
            initialState.add(tmpMoon);
        }
    }

    public void applyXGravity(){
        for (Moon moon:moons){
            for (Moon moon2:moons){
                if (moon.getxPosition()>moon2.getxPosition()){
                    moon.setxVelocity(moon.getxVelocity()-1);
                }
                if (moon.getxPosition()<moon2.getxPosition()){
                    moon.setxVelocity(moon.getxVelocity()+1);
                }
            }
        }
    }

    public void applyYGravity(){
        for (Moon moon:moons){
            for (Moon moon2:moons){
                if (moon.getyPosition()>moon2.getyPosition()){
                    moon.setyVelocity(moon.getyVelocity()-1);
                }
                if (moon.getyPosition()<moon2.getyPosition()){
                    moon.setyVelocity(moon.getyVelocity()+1);
                }
            }
        }
    }

    public void applyZGravity(){
        for (Moon moon:moons){
            for (Moon moon2:moons){
                if (moon.getzPosition()>moon2.getzPosition()){
                    moon.setzVelocity(moon.getzVelocity()-1);
                }
                if (moon.getzPosition()<moon2.getzPosition()){
                    moon.setzVelocity(moon.getzVelocity()+1);
                }
            }
        }
    }

    public void applyGravity(){
        applyXGravity();
        applyYGravity();
        applyZGravity();
    }

    public void applyXVelocity(){
        for (Moon moon:moons){
            moon.setxPosition(moon.getxPosition()+moon.getxVelocity());
        }
    }

    public void applyYVelocity(){
        for (Moon moon:moons){
            moon.setyPosition(moon.getyPosition()+moon.getyVelocity());
        }
    }

    public void applyZVelocity(){
        for (Moon moon:moons){
            moon.setzPosition(moon.getzPosition()+moon.getzVelocity());
        }
    }

    public void applyVelocity(){
        applyXVelocity();
        applyYVelocity();
        applyZVelocity();
    }

    public void printMoons(){
        moons.forEach(System.out::println);
    }

    public int calculateEnergy(){
        int energy = 0;
        for (Moon moon:moons){
            moon.calculateEnergies();
            energy+=moon.getTotalEnergy();
        }
        return energy;
    }

    public boolean checkIfXMatchesInitialState(){
        return  moons.get(0).getxPosition()==initialState.get(0).getxPosition()
                && moons.get(0).getxVelocity()==initialState.get(0).getxVelocity()
                && moons.get(1).getxPosition()==initialState.get(1).getxPosition()
                && moons.get(1).getxVelocity()==initialState.get(1).getxVelocity()
                && moons.get(2).getxPosition()==initialState.get(2).getxPosition()
                && moons.get(2).getxVelocity()==initialState.get(2).getxVelocity()
                && moons.get(3).getxPosition()==initialState.get(3).getxPosition()
                && moons.get(3).getxVelocity()==initialState.get(3).getxVelocity();
    }

    public boolean checkIfYMatchesInitialState(){
        return  moons.get(0).getyPosition()==initialState.get(0).getyPosition()
                && moons.get(0).getyVelocity()==initialState.get(0).getyVelocity()
                && moons.get(1).getyPosition()==initialState.get(1).getyPosition()
                && moons.get(1).getyVelocity()==initialState.get(1).getyVelocity()
                && moons.get(2).getyPosition()==initialState.get(2).getyPosition()
                && moons.get(2).getyVelocity()==initialState.get(2).getyVelocity()
                && moons.get(3).getyPosition()==initialState.get(3).getyPosition()
                && moons.get(3).getyVelocity()==initialState.get(3).getyVelocity();
    }

    public boolean checkIfZMatchesInitialState(){
        return  moons.get(0).getzPosition()==initialState.get(0).getzPosition()
                && moons.get(0).getzVelocity()==initialState.get(0).getzVelocity()
                && moons.get(1).getzPosition()==initialState.get(1).getzPosition()
                && moons.get(1).getzVelocity()==initialState.get(1).getzVelocity()
                && moons.get(2).getzPosition()==initialState.get(2).getzPosition()
                && moons.get(2).getzVelocity()==initialState.get(2).getzVelocity()
                && moons.get(3).getzPosition()==initialState.get(3).getzPosition()
                && moons.get(3).getzVelocity()==initialState.get(3).getzVelocity();
    }

    public void bringBackInitialState(){
        for (int i=0; i<4; i++) {
            Moon moon = moons.get(i);
            moon.setxPosition(initialState.get(i).getxPosition());
            moon.setyPosition(initialState.get(i).getyPosition());
            moon.setzPosition(initialState.get(i).getzPosition());
            moon.setxVelocity(0);
            moon.setyVelocity(0);
            moon.setzVelocity(0);
        }
    }
}
