import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TipoServico {
    int id;
    String nome;
    String descricao;
    double preco;

    public TipoServico(int id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String toString() {
        return nome + " - R$" + preco;
    }
}

class Cliente {
    String nome;
    String cpf;
    String telefone;
    String email;
    String placa;

    public Cliente(String nome, String cpf, String telefone, String email, String placa) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.placa = placa;
    }

    public String toString() {
        return nome + " (" + placa + ")";
    }
}

class Agendamento {
    int id;
    Cliente cliente;
    TipoServico tipoServico;
    LocalDateTime dataHora;
    String status;

    public Agendamento(int id, Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora, String status) {
        this.id = id;
        this.cliente = cliente;
        this.tipoServico = tipoServico;
        this.dataHora = dataHora;
        this.status = status;
    }
}

public class LavaRapido extends JFrame {
    private JTextField txtNome, txtCpf, txtTelefone, txtEmail, txtPlaca;
    private JComboBox<TipoServico> comboServico;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<TipoServico> servicos = new ArrayList<>();
    private List<Agendamento> agendamentos = new ArrayList<>();
    private int agendamentoId = 1;

    public LavaRapido() {
        setTitle("Lava Rápido");
        setSize(850, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        servicos.add(new TipoServico(1, "Lavagem Simples", "Somente exterior", 25.00));
        servicos.add(new TipoServico(2, "Lavagem Completa", "Exterior + Interior", 45.00));
        servicos.add(new TipoServico(3, "Lavagem com Cera", "Completa + Cera", 60.00));
        servicos.add(new TipoServico(4, "Detalhada", "Completa + Cera + Motor", 90.00));

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 80, 25);
        add(lblNome);
        txtNome = new JTextField();
        txtNome.setBounds(100, 10, 200, 25);
        add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(10, 40, 80, 25);
        add(lblCpf);
        txtCpf = new JTextField();
        txtCpf.setBounds(100, 40, 200, 25);
        add(txtCpf);

        JLabel lblTel = new JLabel("Telefone:");
        lblTel.setBounds(10, 70, 80, 25);
        add(lblTel);
        txtTelefone = new JTextField();
        txtTelefone.setBounds(100, 70, 200, 25);
        add(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 100, 80, 25);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(100, 100, 200, 25);
        add(txtEmail);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setBounds(10, 130, 80, 25);
        add(lblPlaca);
        txtPlaca = new JTextField();
        txtPlaca.setBounds(100, 130, 200, 25);
        add(txtPlaca);

        JLabel lblServico = new JLabel("Tipo de Serviço:");
        lblServico.setBounds(10, 160, 120, 25);
        add(lblServico);
        comboServico = new JComboBox<>(servicos.toArray(new TipoServico[0]));
        comboServico.setBounds(130, 160, 250, 25);
        add(comboServico);

        JButton btnAgendar = new JButton("Agendar Serviço");
        btnAgendar.setBounds(400, 160, 160, 25);
        add(btnAgendar);

        tableModel = new DefaultTableModel(new String[]{"ID", "Cliente", "Serviço", "Data/Hora", "Status"}, 0);
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 210, 810, 280);
        add(scroll);

        btnAgendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente(txtNome.getText(), txtCpf.getText(), txtTelefone.getText(), txtEmail.getText(), txtPlaca.getText());
                TipoServico tipoServico = (TipoServico) comboServico.getSelectedItem();
                Agendamento ag = new Agendamento(agendamentoId++, cliente, tipoServico, LocalDateTime.now(), "Agendado");
                agendamentos.add(ag);
                atualizarTabela();
                limparCampos();
            }
        });
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        for (Agendamento ag : agendamentos) {
            tableModel.addRow(new Object[]{
                ag.id,
                ag.cliente.toString(),
                ag.tipoServico.nome,
                ag.dataHora.toString(),
                ag.status
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
        new LavaRapido().setVisible(true);
    }
}
