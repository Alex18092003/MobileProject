package com.example.mobileproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

public class Mask implements Parcelable {
    private int Kod_fact;
    private  String Fact;
    private  String Link;
    private  String Images;
    private int Kod_topic;

    public   Mask(Parcel in)
    {
        Kod_fact = in.readInt();
        Fact = in.readString();
        Images = in.readString();
        Link = in.readString();
        Kod_topic = in.readInt();
    }

    public static  final  Creator<Mask> CREATOR = new Creator<Mask>() {
        @Override
        public Mask createFromParcel(Parcel in) {
            return new Mask(in);
        }

        @Override
        public Mask[] newArray(int size) {
            return new Mask[size];
        }
    };

    public void setKod_fact (int Kod_fact)
    {
        this.Kod_fact = Kod_fact;
    }

    public  void  setFact (String fact)
    {
        Fact = fact;
    }
    public  void  setImages (String images)
    {
        Images = images;
    }
    public  void  setLink (String link)
    {
        Link = link;
    }
    public  void  setKod_topic (int kod_topic)
    {
        Kod_topic = kod_topic;
    }

public Mask(int Kod_fact, String fact, String images, String link, int kod_topic)
{
    this.Kod_fact = Kod_fact;
    Fact = fact;
    Images = images;
    Link = link;
    Kod_topic = kod_topic;
}

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(Kod_fact);
        parcel.writeString(Fact);
        parcel.writeString(Images);
        parcel.writeString(Link);
        parcel.writeInt(Kod_topic);
    }

    public int getID()
    {
        return Kod_fact;
    }
    public String getFact() {
        return Fact;
    }
    public String getImages() {
        return Images;
    }
    public String getLink() {
        return Link;
    }
    public int getKod_topic() {
        return Kod_topic;
    }
}
