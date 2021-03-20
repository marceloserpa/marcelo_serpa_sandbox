package com.marceloserpa.reactorkafkapoc;

import java.util.List;
import java.util.UUID;

public class Sale {

    private UUID id;
    private UUID clientId;
    private List<UUID> itens;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public List<UUID> getItens() {
        return itens;
    }

    public void setItens(List<UUID> itens) {
        this.itens = itens;
    }
}
