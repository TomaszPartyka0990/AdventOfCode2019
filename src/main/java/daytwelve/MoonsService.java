package daytwelve;

import java.util.List;

public class MoonsService {
    List<Moon> moons;

    public MoonsService(List<Moon> moons) {
        this.moons = moons;
    }

    public void applyGravity(){
        for (Moon moon:moons){
            for (Moon moon2:moons){
                if (moon.getxPosition()>moon2.getxPosition()){
                    moon.setxVelocity(moon.getxVelocity()-1);
                }
                if (moon.getxPosition()<moon2.getxPosition()){
                    moon.setxVelocity(moon.getxVelocity()+1);
                }
                if (moon.getyPosition()>moon2.getyPosition()){
                    moon.setyVelocity(moon.getyVelocity()-1);
                }
                if (moon.getyPosition()<moon2.getyPosition()){
                    moon.setyVelocity(moon.getyVelocity()+1);
                }
                if (moon.getzPosition()>moon2.getzPosition()){
                    moon.setzVelocity(moon.getzVelocity()-1);
                }
                if (moon.getzPosition()<moon2.getzPosition()){
                    moon.setzVelocity(moon.getzVelocity()+1);
                }
            }
        }
    }

    public void applyVelocity(){
        for (Moon moon:moons){
            moon.setxPosition(moon.getxPosition()+moon.getxVelocity());
            moon.setyPosition(moon.getyPosition()+moon.getyVelocity());
            moon.setzPosition(moon.getzPosition()+moon.getzVelocity());
        }
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
}
