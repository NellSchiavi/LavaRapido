package view;

import model.*;
import controller.LavaRapidoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class LavaRapidoView extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone, txtEmail, txtPlaca;
    private JComboBox<TipoServico> comboServico;
    private JTable table;
    private DefaultTableModel tableModel;
    private LavaRapidoController controller = new LavaRapidoController();

    public LavaRapidoView() {
        setTitle("Lava Rapido");
        setSize(850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        TipoServico[] servicos = {
            new TipoServico(1, "Simples", "Exterior apenas", 25.00),
            new TipoServico(2, "Completa", "Exterior e interior", 45.00),
            new TipoServico(3, "Com Cera", "Completa com cera", 60.00),
            new TipoServico(4, "Detalhada", "Completa com motor", 90.00)
        };

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 80, 25); add(lblNome);
        txtNome = new JTextField(); txtNome.setBounds(100, 10, 200, 25); add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 40, 80, 25); add(lblCpf);
        txtCpf = new JTextField(); txtCpf.setBounds(100, 40, 200, 25); add(txtCpf);

        JLabel lblTel = new JLabel("Telefone:");
        lblTel.setBounds(10, 70, 80, 25); add(lblTel);
        txtTelefone = new JTextField(); txtTelefone.setBounds(100, 70, 200, 25); add(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 100, 80, 25); add(lblEmail);
        txtEmail = new JTextField(); txtEmail.setBounds(100, 100, 200, 25); add(txtEmail);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(10, 130, 80, 25); add(lblPlaca);
        txtPlaca = new JTextField(); txtPlaca.setBounds(100, 130, 200, 25); add(txtPlaca);

        JLabel lblServico = new JLabel("Servico:");
        lblServico.setBounds(10, 160, 120, 25); add(lblServico);
        comboServico = new JComboBox<>(servicos); comboServico.setBounds(100, 160, 250, 25); add(comboServico);

        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(380, 160, 120, 25); add(btnAgendar);

        tableModel = new DefaultTableModel(new String[]{"ID", "Cliente", "Servico", "DataHora", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 210, 810, 280); add(scroll);

        btnAgendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente(
                    txtNome.getText(), txtCpf.getText(),
                    txtTelefone.getText(), txtEmail.getText(),
                    txtPlaca.getText()
                );
                TipoServico tipo = (TipoServico) comboServico.getSelectedItem();
                controller.agendarServico(cliente, tipo);
                atualizarTabela();
                limparCampos();
            }
        });
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Agendamento ag : controller.listarAgendamentos()) {
            tableModel.addRow(new Object[]{
                ag.getId(),
                ag.getCliente().toString(),
                ag.getTipoServico().getNome(),
                ag.getDataHora().toString(),
                ag.getStatus()
            });
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtPlaca.setText("");
        comboServico.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new LavaRapidoView().setVisible(true);
    }
}
