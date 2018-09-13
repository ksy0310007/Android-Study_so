package renomedia.co.kr.adapterviewtest;
/*
* 모델 클래스 : 현살세계에 존재하는 사물 또는 객체를 표현하기 위해 클래스로 작성한 것.
*
 */
public class Weather {

    private String city; // 도시명
    private String temp; // 기온
    private String weather; // 날씨


    // 생성자 : 객체 생성을 편하게 하기위함.
    public Weather(String city, String temp, String weather) {
        this.city = city;
        this.temp = temp;
        this.weather = weather;
    }

    //캡슐화 : 다른 클래스에서 속성에 직접 접근을 막는 것, getter,setter 로 객체의 속성에 접근
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    // 디버깅이나 로그에서 정보를 확인하기 위함. 해당 객체의 정보를 표시
    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", temp='" + temp + '\'' +
                ", weather='" + weather + '\'' +
                '}';
    }
}
