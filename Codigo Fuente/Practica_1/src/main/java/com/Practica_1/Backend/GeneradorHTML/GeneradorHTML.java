package com.Practica_1.Backend.GeneradorHTML;

import com.Practica_1.Backend.Datos.Data_Actividad;
import com.Practica_1.Backend.Datos.Data_Evento;
import com.Practica_1.Backend.Datos.Data_Participante;

public class GeneradorHTML {

    public static String CertificadoHTML(Data_Participante data_Par, String codigoEvento, Data_Actividad[] data_Act) {

        String stringHTML = null;

        stringHTML = ("<html>");
        stringHTML = (stringHTML + "<head>");
        stringHTML = (stringHTML + "<title>Certificado de asistencia</title>");
        stringHTML = (stringHTML + "</head>");
        stringHTML = (stringHTML + "<body>");

        stringHTML = (stringHTML + "<FONT SIZE=5><p>Certificado De Asistencia</p></font>");
        stringHTML = (stringHTML + "<FONT SIZE=2><p>Se le otorga este certificado a: "
                + data_Par.getNombreParticipante() + ".</p></font>");
        stringHTML = (stringHTML + "<FONT SIZE=2><p>Por asistir a actividades del evento: " + codigoEvento
                + ".</p></font>");

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

    public static String ReporteParticipantesHTML(Data_Participante[] participantes) {

        String stringHTML = null;

        stringHTML = ("<html>");
        stringHTML = (stringHTML + "<head>");
        stringHTML = (stringHTML + "<title>Reporte de participantes</title>");
        stringHTML = (stringHTML + "</head>");
        stringHTML = (stringHTML + "<body>");

        stringHTML = (stringHTML + "<FONT SIZE=5><p>Reporte de participantes</p></font>");

        stringHTML = (stringHTML + "<table border=\"1\"><tr><th>CORREO ELECTRÓNICO</th>");
        stringHTML = (stringHTML + "<th>TIPO</th>");
        stringHTML = (stringHTML + "<th>NOMBRE COMPLETO</th>");
        stringHTML = (stringHTML + "<th>INSTITUCIÓN DE PROCEDENCIA</th>");
        stringHTML = (stringHTML + "<th>FUE VALIDADO O NO</th></tr>");

        for (Data_Participante data : participantes) {
            stringHTML = (stringHTML + "<tr><th>" + data.getCorreoParticipante() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getTipoParticipante().toString() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getNombreParticipante() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getInstitucionParticipante() + "</th>");

            if (data.getAsistenciaValidada()) {
                stringHTML = (stringHTML + "<th>Si</th></tr>");
            } else {
                stringHTML = (stringHTML + "<th>No</th></tr>");
            }

        }

        stringHTML = (stringHTML + "</table>");
        stringHTML = (stringHTML + "</body>");
        stringHTML = (stringHTML + "</html>");

        return stringHTML;
    }

    public static String ReporteActividadesHTML(Data_Actividad[] actividades) {

        String stringHTML = "";

        stringHTML = ("<html>");
        stringHTML = (stringHTML + "<head>");
        stringHTML = (stringHTML + "<title>Reporte de actividades</title>");
        stringHTML = (stringHTML + "</head>");
        stringHTML = (stringHTML + "<body>");

        stringHTML = (stringHTML + "<FONT SIZE=5><p>Reporte de participantes</p></font>");

        stringHTML = (stringHTML + "<table border=\"1\"><tr><th>CÓDIGO DE LA ACTIVIDAD</th>");
        stringHTML = (stringHTML + "<th>CÓDIGO DEL EVENTO</th>");
        stringHTML = (stringHTML + "<th>TÍTULO DE LA ACTIVIDAD</th>");
        stringHTML = (stringHTML + "<th>NOMBRE DEL ENCARGADO</th>");
        stringHTML = (stringHTML + "<th>HORA DE INICIO</th>");
        stringHTML = (stringHTML + "<th>CUPO MÁXIMO</th>");
        stringHTML = (stringHTML + "<th>CANTIDAD DE PARTICIPANTES</th></tr>");

        for (Data_Actividad data : actividades) {
            stringHTML = (stringHTML + "<tr><th>" + data.getCodigoActividad() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getCodigoEvento() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getTituloActividad() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getCorreoImpartidor() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getHoraInicio().toString() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getCupoMaximo() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getParticipantes() + "</th></tr>");

        }

        stringHTML = (stringHTML + "</table>");
        stringHTML = (stringHTML + "</body>");
        stringHTML = (stringHTML + "</html>");

        return stringHTML;
    }

    public static String ReporteEventosInicioHTML() {

        String stringHTML = null;

        stringHTML = ("<html>");
        stringHTML = (stringHTML + "<head>");
        stringHTML = (stringHTML + "<title>Reporte de actividades</title>");
        stringHTML = (stringHTML + "</head>");
        stringHTML = (stringHTML + "<body>");

        stringHTML = (stringHTML + "<FONT SIZE=6><p>Reporte de eventos</p></font>");

        return stringHTML;
    }

    public static String ReporteEventosHTML(int numero, Data_Evento evento, Data_Participante[] participantes, int montoTotal,
        int participantesValidos, int participantesNoValidos) {

        String stringHTML = "";

        stringHTML = (stringHTML + "<FONT SIZE=5><p>Evento No. " + (numero + 1) + "</p></font>");

        stringHTML = (stringHTML + "<p>CODIGO DE EVENTO:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + evento.getCodigoEvento() + "</p>");
        stringHTML = (stringHTML + "<p>FECHA DE EVENTO:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + evento.getFechaEvento().toString() + "</p>");
        stringHTML = (stringHTML + "<p>TITULO DE EVENTO:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + evento.getTituloEvento() + "</p>");
        stringHTML = (stringHTML + "<p>TIPO DE EVENTO:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + evento.getTipoEvento().toString() + "</p>");
        stringHTML = (stringHTML + "<p>UBICACION:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + evento.getUbicacionEvento() + "</p>");
        stringHTML = (stringHTML + "<p>CUPO MAXIMO:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + evento.getCupoEvento() + "</p>");

        stringHTML = (stringHTML + "<table border=1><tr><th>CORREO DEL PARTICIPANTE</th>");
        stringHTML = (stringHTML + "<th>NOMBRE DEL PARTICIPANTE</th>");
        stringHTML = (stringHTML + "<th>TIPO DE PARTICIPANTE</th>");
        stringHTML = (stringHTML + "<th>MÉTODO DE PAGO</th>");
        stringHTML = (stringHTML + "<th>MONTO PAGADO</th>");

        for (Data_Participante data : participantes) {
            stringHTML = (stringHTML + "<tr><th>" + data.getCorreoParticipante() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getNombreParticipante() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getTipoParticipante().toString() + "</th>");
            stringHTML = (stringHTML + "<th>" + data.getMetodoPago() + "</th>");
            stringHTML = (stringHTML + "<th>" + String.valueOf(data.getMontoPago()) + "</th></tr>");

        }

        stringHTML = (stringHTML + "</table>");

        stringHTML = (stringHTML + "<p>MONTO TOTAL:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + montoTotal + "</p>");
        stringHTML = (stringHTML + "<p>PARTICIPANTES VALIDADOS:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + participantesValidos + "</p>");
        stringHTML = (stringHTML + "<p>PARTICIPANTES NO VALIDADOS:</p>");
        stringHTML = (stringHTML + "<p>&emsp;&emsp;&emsp;&emsp;" + participantesNoValidos + "</p>");

        return stringHTML;

    }

    public static String ReporteEventosFinHTML() {

        String stringHTML = "";

        stringHTML = (stringHTML + "</body>");
        stringHTML = (stringHTML + "</html>");

        return stringHTML;
    }

}
