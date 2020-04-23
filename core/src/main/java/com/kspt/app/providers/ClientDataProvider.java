package com.kspt.app.providers;

import com.kspt.app.entities.actor.Client;
import com.kspt.app.models.response.ResponseOrMessage;
import com.kspt.app.models.table.GridDataModel;
import com.kspt.app.models.table.MetaDataModel;
import com.kspt.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Masha on 18.04.2020
 */
@Component
@Qualifier("clientTable")
public class ClientDataProvider implements IDataProvider {
    ClientRepository clientRepository;

    public ClientDataProvider(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseOrMessage<GridDataModel> getData(Map<String, Object> parameters) {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            return new ResponseOrMessage<>("Clients not found");
        }
        GridDataModel dataModel = new GridDataModel();
        MetaDataModel metaDataModel = new MetaDataModel();

        String[] columns = {"No.", "First Name", "Last Name", "Email","Login", "Phone Number", "Rating","Deleted"};
        metaDataModel.setColumns(columns);
        int countOfClients = clients.size();
        metaDataModel.setTotalCount((long) countOfClients);

        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        clients.forEach(client -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(client.getId());
            row.add(client.getFirstName());
            row.add(client.getLastName());
            row.add(client.getCredentials().getEmail());
            row.add(client.getCredentials().getUsername());
            row.add(client.getPhoneNumber());
            row.add(client.getRating());
            row.add(client.isDeleted());
            data.add(row);
        });
        dataModel.setData(data);
        dataModel.setMetaData(metaDataModel);
        return new ResponseOrMessage<>(dataModel);
    }
}
