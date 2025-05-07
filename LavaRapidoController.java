package controller;

import model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LavaRapidoController {
    private List<Agendamento> agendamentos = new ArrayList<>();
    private int idCounter = 1;

    public void agendarServico(Cliente cliente, TipoServico tipoServico) {
        Agendamento agendamento = new Agendamento(idCounter++, cliente, tipoServico, LocalDateTime.now(), "Agendado");
        agendamentos.add(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentos;
    }
}
