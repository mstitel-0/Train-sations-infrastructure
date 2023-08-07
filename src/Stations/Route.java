package Stations;

import java.util.ArrayList;
public class Route {
    private ArrayList<Station> route;

    private int amountOfStations;
    private Station station;
    private String homeStation;
    private String lastStation;
    private int routeDistance;
    public Route(Station... stations) {
        this.route = new ArrayList<>();
        for(Station station : stations){
            this.route.add(station);
            station.setConnected(true);
        }
//        this.route.get(0).genHomeStation();
//        this.route.get(amountOfStations-1).genLastStation();
        this.homeStation = this.route.get(0).getStationName();
        this.lastStation = this.route.get(this.route.size()-1).getStationName();
        sumRouteDistance();
    }
    public void addStation(Station station){
        this.route.add(station);
        station.setConnected(true);
//        this.route.get(0).genHomeStation();
//        this.route.get(amountOfStations-1).genLastStation();
        this.homeStation = this.route.get(0).getStationName();
        this.lastStation = this.route.get(this.route.size()-1).getStationName();
        sumRouteDistance();
    }
    public Route(){
        this.amountOfStations = (int)(Math.random()*2)+4;
        this.route = new ArrayList<>();
        for (int i = 0; i < amountOfStations; i++) {
            station = Station.genStation();
            this.route.add(station);
            station.setConnected(true);
        }
        this.route.get(0).genHomeStation();
        this.route.get(amountOfStations-1).genLastStation();
        this.homeStation = this.route.get(0).getStationName();
        this.lastStation = this.route.get(this.route.size()-1).getStationName();
        sumRouteDistance();
    }
    public static Route genRoute(){

        return new Route();
    }
    public int sumRouteDistance(){
        int x = 0;
       for (int i = 0 ; i < (this.route.size()-1) ; i++){
           x += this.route.get(i).distance;
       }
       return this.routeDistance = (x-this.route.get(0).getDistance());
    }

    public String getHomeStation() {
        return homeStation;
    }

    public String getLastStation() {
        return lastStation;
    }

    public ArrayList<Station> getRoute() {
        return route;
    }


    public int getRouteDistance() {
        return routeDistance;
    }


    @Override
    public String toString() {
        return "Stations.Route" + route + "";
    }
}
