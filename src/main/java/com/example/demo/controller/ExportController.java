package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.service.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controlleur pour réaliser les exports.
 */
@Controller
@RequestMapping("/")
public class ExportController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FactureService factureService;


    @GetMapping("/clients/csv")
        public void clientsCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"clients.csv\"");
            PrintWriter writer = response.getWriter();
            List<Client> allClients = clientService.findAllClients();
            LocalDate now = LocalDate.now();
            writer.println("Id;Nom;Prenom;Date de Naissance;Age");

            for (Client client : allClients) {

                writer.println(client.getId() + ";"
                        + client.getPrenom() + ";"
                        + client.getNom() + ";"
                        + client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
            }
    }
/*
    @GetMapping("/clients/xlsx")
    public void clientsXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.xlsx\"");
        List<Client> allClients = clientService.findAllClients();
        //LocalDate now = LocalDate.now();


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clients");
        Row headerRow = sheet.createRow(0);

        Cell cellNom = headerRow.createCell(0);
        cellNom.setCellValue("Nom");

        Cell cellPrenom = headerRow.createCell(1);
        cellPrenom.setCellValue("Prenom");

        Cell cellDateNaissance = headerRow.createCell(2);
        cellDateNaissance.setCellValue("DateNaissance");

        int iRow = 1;
        for (Client client : allClients) {
            Row row = sheet.createRow(iRow);

            Cell nom = row.createCell(0);
            nom.setCellValue(client.getNom());

            Cell prenom = row.createCell(1);
            prenom.setCellValue(client.getPrenom());

            Cell dateNaissance = row.createCell(2);
            dateNaissance.setCellValue(client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));

            iRow = iRow +1;

        }

        workbook.write(response.getOutputStream());
        workbook.close();

    }


    @GetMapping("/clients/{id}/factures/xlsx")
    public void facturesXLSX(@PathVariable("id") Long clientId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"totalfactures.xlsx\"");

        List<Facture> factures = factureService.findFactureClient(clientId);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Factures");
        Row headerRow = sheet.createRow(0);

        Cell cellClient = headerRow.createCell(0);
        cellClient.setCellValue("Client");

        Cell cellTotal = headerRow.createCell(1);
        cellTotal.setCellValue("Total");


        int iRow = 1;
        for (Facture facture : factures) {
            Row row = sheet.createRow(iRow);

            Cell client = row.createCell(0);
            client.setCellValue(facture.getClient().getId());

            Cell total = row.createCell(1);
            total.setCellValue(facture.getTotal());

            iRow = iRow +1;

        }

        workbook.write(response.getOutputStream());
        workbook.close();

    }
*/
    @GetMapping("/factures/xlsx")
    public void facturesclientsXLSX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"factures.xlsx\"");
        List<Client> allClients = clientService.findAllClients();

        //Création cdu workbook
        Workbook workbook = new XSSFWorkbook();

        //Boucle sur les clients
        for (Client client : allClients) {
             //Création de l'onglet Client avec toutes les info
            Sheet sheet = workbook.createSheet(client.getNom());
            Row headerRow = sheet.createRow(0);

            //Création des noms colonnes
            Cell cellNom = headerRow.createCell(0);
            cellNom.setCellValue("Nom");

            Cell cellPrenom = headerRow.createCell(1);
            cellPrenom.setCellValue("Prenom");

            Cell cellDateNaissance = headerRow.createCell(2);
            cellDateNaissance.setCellValue("DateNaissance");

            Cell cellId = headerRow.createCell(3);
            cellId.setCellValue("Id");

            Row row = sheet.createRow(1);

            //Récupération des info
            Cell nom = row.createCell(0);
            nom.setCellValue(client.getNom());

            Cell prenom = row.createCell(1);
            prenom.setCellValue(client.getPrenom());

            Cell dateNaissance = row.createCell(2);
            dateNaissance.setCellValue(client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));

            Cell id = row.createCell(3);
            id.setCellValue(client.getId());

            //Récupération des facture par Id client
            List<Facture> factures = factureService.findFactureClient(client.getId());
            //LocalDate now = LocalDate.now();

            //i nous permet d'avoir le nom du bon client
            int i =1;
            //Boucle sur les factures
            for (Facture facture : factures) {

                //Onglet facture
                Sheet sheet2 = workbook.createSheet("Facture de " + client.getNom() + i);
                Row headerRow2 = sheet2.createRow(1);

                //Création des noms des colonnes
                Cell cellProduit = headerRow2.createCell(0);
                cellProduit.setCellValue("Produit");

                Cell cellQuantite = headerRow2.createCell(1);
                cellQuantite.setCellValue("Quantité");

                Cell cellPrix = headerRow2.createCell(2);
                cellPrix.setCellValue("Prix");

                Cell cellSousTotal = headerRow2.createCell(3);
                cellSousTotal.setCellValue("Sous total");

                //Boucle  sur ligne facture
                int frow = 1;
                for (LigneFacture lignefacture : facture.getLigneFactures()) {
                    Row rowBis = sheet2.createRow(frow);

                    Cell cellArticle = rowBis.createCell(0);
                    cellArticle.setCellValue(lignefacture.getArticle().getLibelle());

                    Cell cellQuantiteArticle = rowBis.createCell(1);
                    cellQuantiteArticle.setCellValue(lignefacture.getQuantite());

                    Cell cellPrixArticle = rowBis.createCell(2);
                    cellPrixArticle.setCellValue(lignefacture.getArticle().getPrix());

                    Cell cellSousTotalArticle = rowBis.createCell(3);
                    cellSousTotalArticle.setCellValue(lignefacture.getSousTotal());

                    frow = frow + 1;
                }
                Row rowTotal = sheet2.createRow(frow);
                Cell cellPrixTotal = rowTotal.createCell(3);
                cellPrixTotal.setCellValue(facture.getTotal());

                //Incrémentation de i
                i++ ;
            }
        }

        workbook.write(response.getOutputStream());
        workbook.close();

    }




    }

