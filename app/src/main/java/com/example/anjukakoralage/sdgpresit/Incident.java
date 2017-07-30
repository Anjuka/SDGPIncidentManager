package com.example.anjukakoralage.sdgpresit;

import java.io.Serializable;

/**
 * Created by AnjukaKoralage on 7/29/2017.
 */

public class Incident implements Serializable{




        public String ID;
        public String ReporterName;
        public String IncidentType;
        public String ResponsPerson;
        public String Note;
        public String Date;
        public String Time;
        public String uid;



        public Incident(){

        }

        public Incident(String ID, String reporterName,String Incitype,String responsPerson, String note, String date, String time) {
            this.ID = ID;
            this.ReporterName = reporterName;
            this.IncidentType = Incitype;
            this.ResponsPerson = responsPerson;
            this.Note = note;
            this.Date = date;
            this.Time = time;
        }


    }


