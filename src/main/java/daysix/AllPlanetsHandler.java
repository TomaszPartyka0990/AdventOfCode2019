package daysix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AllPlanetsHandler {
    private List<Planet> allPlanets;

    AllPlanetsHandler(){
        allPlanets = new ArrayList<>();
    }

    private void addPlanetToList(Planet planet){
        allPlanets.add(planet);
    }

    void getOrbitInformationAndActualizeSet(String data){
        String[] planetNames = data.split("\\)");
        Planet planetOne = new Planet(planetNames[0]);
        Planet planetTwo = new Planet(planetNames[1]);
        if(!allPlanets.contains(planetTwo)){
            planetTwo.addPlanetsToList(Arrays.asList(planetNames[0]));
            if(!allPlanets.contains(planetOne)) {
                allPlanets.add(planetOne);
            } else {
                int planetOneIndex = allPlanets.indexOf(planetOne);
                planetOne = allPlanets.get(planetOneIndex);
                planetTwo.addPlanetsToList(planetOne.getPlanetNamesThatThisPlanetOrbitsAround());
            }
            addPlanetToList(planetTwo);
        } else {
            int planetTwoIndex = allPlanets.indexOf(planetTwo);
            planetTwo = allPlanets.get(planetTwoIndex);
            planetTwo.addPlanetsToList(Arrays.asList(planetNames[0]));
            if(!allPlanets.contains(planetOne)) {
                allPlanets.add(planetOne);
            } else {
                int planetOneIndex = allPlanets.indexOf(planetOne);
                planetOne = allPlanets.get(planetOneIndex);
                planetTwo.addPlanetsToList(planetOne.getPlanetNamesThatThisPlanetOrbitsAround());
            }
            for(Planet planet:allPlanets){
                if (planet.getPlanetNamesThatThisPlanetOrbitsAround().contains(planetTwo.getName())){
                    planet.addPlanetsToList(planetTwo.getPlanetNamesThatThisPlanetOrbitsAround());
                }
            }
            allPlanets.set(planetTwoIndex, planetTwo);
        }
    }

    int getAllOrbitsNumber(){
        return allPlanets.stream().mapToInt(Planet::getNumberOfOrbits).sum();
    }

    void setNumberOfOrbitsForAllPlanets(){
        for(Planet planet:allPlanets){
            planet.setNumberOfOrbits(planet.getPlanetNamesThatThisPlanetOrbitsAround().size());
        }
    }

    int getNumberOfJumpsToSanta(){
        int myIndex = allPlanets.indexOf(new Planet("YOU"));
        int santaIndex = allPlanets.indexOf(new Planet("SAN"));
        Planet myObject = allPlanets.get(myIndex);
        Planet santaObject = allPlanets.get((santaIndex));
        List<String> myPlanetsImOrbitingAround = myObject.getPlanetNamesThatThisPlanetOrbitsAround();
        List<String> santaPlanetsHesOrbitingAround = santaObject.getPlanetNamesThatThisPlanetOrbitsAround();
        int jumpsCounter;
        String commonPlanet = "";
        for (String planet:myPlanetsImOrbitingAround){
            if (santaPlanetsHesOrbitingAround.contains(planet)){
                commonPlanet = planet;
                break;
            }
        }
        jumpsCounter = myPlanetsImOrbitingAround.indexOf(commonPlanet) + santaPlanetsHesOrbitingAround.indexOf(commonPlanet);
        return jumpsCounter;
    }
}
