public class Airport {
    private String code, country, city;
    private double latitude, longitude;

    public Airport(String code, String country, String city, double latitude, double longitude) {
        this.code = code;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCode() { return code; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
