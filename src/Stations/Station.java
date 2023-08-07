package Stations;

public class Station {
    public String stationName;
    public String[][] allStations = {{"Nova Station", "Starlight Station", "Hyperion Station", "Astral Station", "Cosmos Station", "Celestial Station", "Infinity Station", "Galactic Station", "Nebula Station", "Zodiac Station", "Aquarius Station", "Capricorn Station", "Leo Station", "Scorpio Station", "Taurus Station", "Virgo Station", "Libra Station", "Aquila Station", "Draco Station", "Aurora Station","Vega Station", "Serpens Station", "Pegasus Station", "Cygnus Station", "Centaurus Station", "Perseus Station", "Hercules Station", "Lyra Station", "Cassiopeia Station", "Altair Station", "Betelgeuse Station", "Antares Station", "Polaris Station", "Sirius Station", "Procyon Station", "Rigel Station", "Bellatrix Station", "Alphard Station", "Regulus Station", "Deneb Station", "Algol Station", "Spica Station", "Helix Station", "Andromeda's Eye Station", "Andromeda's Beacon Station","Kyiv", "Kharkiv", "Lviv", "Odesa", "Dnipro", "Donetsk", "Zaporizhzhia", "Kryvyi Rih", "Mykolaiv", "Mariupol", "Luhansk", "Vinnytsia", "Poltava", "Chernivtsi", "Cherkasy", "Chernihiv"},
                                     {"Polaris Prime Station", "Sagittarius Station", "Corona Station", "Cassiopeia's Throne Station", "Pleiades Station", "Hypernova Station", "Eridanus Station", "Quasar Station", "Crux Station", "Helios Station", "Galaxia Station", "Cepheus Station", "Regulus Outpost Station", "Sirius Sentinel Station","Pear Station", "Apple Station", "Banana Station", "Coconut Station"
                                     ,"Hydra's Lair Station", "Scorpius Station", "Proxima Centauri Station", "Gemini Gateway Station", "Ursa Minor Station", "Corvus Station", "Mango Station", "Papaya Station", "Guava Station", "Kiwi Station", "Pineapple Station", "Starfruit Station", "Passionfruit Station", "Lychee Station","Cherry Station", "Cranberry Station", "Fig Station", "Pomegranate Station", "Tangerine Station" , "Kavun Station"
                                     ,"Dragonfruit Station", "Persimmon Station", "Grapefruit Station", "Blackberry Station", "Blueberry Station", "Raspberry Station", "Strawberry Station", "Watermelon Station", "Cantaloupe Station", "Honeydew Station", "Kiwano Station", "Nectarine Station", "Peach Station", "Plum Station", "Apricot Station", "Lemon Station", "Lime Station", "Orange Station", "Grape Station",  "Avocado Station","Warsaw", "Krakow", "Gdansk", "Poznan", "Wroclaw", "Lodz", "Katowice", "Szczecin", "Gdynia", "Bialystok"},
                                     {"Crimson Station", "Turquoise Station", "Saffron Station", "Jade Station", "Azure Station", "Magenta Station", "Amber Station", "Cobalt Station", "Olive Station", "Indigo Station", "Sienna Station", "Chartreuse Station", "Cerulean Station", "Violet Station", "Scarlet Station", "Maroon Station", "Teal Station", "Lilac Station", "Fuchsia Station", "Goldenrod Station","Sumy", "Kherson", "Zhytomyr", "Khmelnytskyi", "Rivne", "Ivano-Frankivsk", "Kamianske", "Ternopil", "Lutsk", "Bila Tserkva", "Kropyvnytskyi", "Mukachevo", "Yevpatoria", "Uzhhorod"}};
    public int distance;
    public boolean isConnected = false;
    public Station() {
        genStationName();
        this.distance = (int)((Math.random()*400)+2000);
    }

    public Station(String stationName) {
        this.stationName = stationName;
        this.distance = (int)((Math.random()*1500)+2000);
    }

    public String getStationName() {
        return stationName;
    }

    public static Station genStation(){
        return new Station();
    }
    public String genStationName(){
        return this.stationName = allStations[1][(int)(Math.random()*allStations[1].length)];
    }
    public String genHomeStation(){
        return this.stationName = allStations[0][(int)(Math.random()* allStations[0].length)];
    }
    public String genLastStation(){
        return this.stationName = allStations[2][(int)(Math.random()*allStations[2].length)];
    }
    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "{" + stationName + " - " + distance + " km}";
    }
}
