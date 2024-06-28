package br.com.exercicio.alpe.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Datas {

    // Método que converte uma String para LocalDate
    public static LocalDate stringToLocalDate(String dateString, String pattern) {
        // Cria um formatador de data com o padrão fornecido
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        try {
            // Converte a string para LocalDate usando o formatador
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            // Trata a exceção caso a string não esteja no formato esperado
            System.out.println("Erro ao converter a string para LocalDate: " + e.getMessage());
            return null;
        }
    }

}