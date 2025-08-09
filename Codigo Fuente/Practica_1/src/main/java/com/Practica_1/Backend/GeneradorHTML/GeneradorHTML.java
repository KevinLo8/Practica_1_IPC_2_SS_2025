package com.Practica_1.Backend.GeneradorHTML;

import com.Practica_1.Backend.Datos.Data_Actividad;
import com.Practica_1.Backend.Datos.Data_Participante;

public class GeneradorHTML {

    public static String CertificadoHTML(Data_Participante data_Par, String codigoEvento, Data_Actividad[] data_Act){

        String stringHTML = null;

        
        stringHTML = ("<html>");
        stringHTML = (stringHTML + "<head>");
        stringHTML = (stringHTML + "<title>" + "Certificado de asistencia" + "</title>");
        stringHTML = (stringHTML + "</head>");
        stringHTML = (stringHTML + "<body>");

        stringHTML = (stringHTML + "<FONT SIZE=5><p>Certificado De Asistencia</p></font>");
        stringHTML = (stringHTML + "<FONT SIZE=2><p>Se le otorga este certificado a: " + data_Par.getNombreParticipante() + ".</p></font>");
        stringHTML = (stringHTML + "<FONT SIZE=2><p>Por asistir a actividades del evento: " + codigoEvento + ".</p></font>");

        stringHTML = (stringHTML + "<table border=\"1\"><tr><th>CODIGO</th>");
        stringHTML = (stringHTML + "<th>TIPO</th>");
        stringHTML = (stringHTML + "<th>TITULO</th>");
        stringHTML = (stringHTML + "<th>HORA INICIO</th>");
        stringHTML = (stringHTML + "<th>HORA FIN</th></tr>");

        for (Data_Actividad data : data_Act) {
            stringHTML = (stringHTML + "<tr><th>" + data.getCodigoActividad() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getTipoActividad().toString() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getTituloActividad() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getHoraInicio() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getHoraFin() + "</th></tr>");

        }

        stringHTML = (stringHTML + "</table>");
        stringHTML = (stringHTML + "</body>");
        stringHTML = (stringHTML + "</html>");

        return stringHTML;
    }

}
