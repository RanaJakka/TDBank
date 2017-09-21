package tdbank.com.tdbank.services;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Enumeration;

/**
 * Created by Rana Prathap on 9/20/2017.
 */

public class Weather_report implements Parcelable {


    private double lat_val;
    private double long_val;
public Weather_report()
{

}
    protected Weather_report(Parcel in) {
        lat_val = in.readDouble();
        long_val = in.readDouble();
        time_zone = in.readString();
        cur_summary = in.readString();
        icon = in.readInt();
    }

    public static final Creator<Weather_report> CREATOR = new Creator<Weather_report>() {
        @Override
        public Weather_report createFromParcel(Parcel in) {
            return new Weather_report(in);
        }

        @Override
        public Weather_report[] newArray(int size) {
            return new Weather_report[size];
        }
    };

    public double getLat_val() {
        return lat_val;
    }

    public void setLat_val(double lat_val) {
        this.lat_val = lat_val;
    }

    public double getLong_val() {
        return long_val;
    }

    public void setLong_val(double long_val) {
        this.long_val = long_val;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getCur_summary() {
        return cur_summary;
    }

    public void setCur_summary(String cur_summary) {
        this.cur_summary = cur_summary;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(String icon) {

        switch (icon) {
            case "clear_day":
                this.icon = 1;
                break;
            case "clear_night":
                this.icon = 1;
                break;
            case "rain":
                this.icon = 2;
                break;
            case "snow":
                this.icon = 3;
                break;
            case "sleet":
                this.icon = 3;
                break;
            case "wind":
                this.icon = 4;
                break;
            case "fog":

                this.icon = 4;
                break;
            case "cloudy":
                this.icon = 5;
                break;
            case "partly_cloudy_day":
                this.icon = 5;
                break;
            case "partly_cloudy_night":
                this.icon = 5;
                break;

            case "hail":

                this.icon = 6;
                break;
            case "thunderstorm":

                this.icon = 6;
                break;
            case "tornado":

                this.icon = 6;
                break;

        }


    }

    private String time_zone;
    private String cur_summary;
    private int icon;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lat_val);
        parcel.writeDouble(long_val);
        parcel.writeString(time_zone);
        parcel.writeString(cur_summary);
        parcel.writeInt(icon);
    }


}
