package com.example.demo.service;

import com.example.demo.entity.Client;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ExportServiceTest {

   /* @Mock
    private ClientService mockClientService;

    @InjectMocks
    private ExportService exportService;

    @Test
    public void clientsCSV() throws IOException {
        StringWriter stringWriter = new StringWriter();
        exportService.clientsCSV(stringWriter);

        Assertions.assertThat(stringWriter.toString()).contains("Id;Nom;");
    }


    @Test
    public void clientsCSV_avecUnClient() throws IOException {
        Client client = new Client();
        client.setNom ("Chimoi");
        client.setPrenom("Dans");
        client.setDateNaissance(LocalDate.now());

        Mockito.when(mockClientService.findAllClients()).thenReturn(Arrays.asList(client));

        StringWriter stringWriter = new StringWriter();
        exportService.clientsCSV(stringWriter);
        Assertions.assertThat(stringWriter.toString()).contains("Chimoi;Dans");
    }*/

}