package com.example.demo.repository;

import com.example.demo.entity.Client;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {
/*
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void save(){
        //GIVEN
        Client client = new Client();
        client.setNom("COLLION");
        client.setPrenom("Xavier");

        //WHEN
        clientRepository.save(client);

        //THEN
        List<Client> allClients = clientRepository.findAll();
        Assertions.assertThat(clientRepository.findAll()).hasSize(1);
        Client uniqueClient = allClients.get(0);
        Assertions.assertThat(allClients.get(0).getId()).isNotNull();
        Assertions.assertThat(allClients.get(0).getPrenom()).isEqualTo("Xavier");
        Assertions.assertThat(allClients.get(0).getNom()).isEqualToIgnoringCase("cOlliOn");
    }*/
}