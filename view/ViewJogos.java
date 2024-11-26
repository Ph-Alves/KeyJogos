package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import model.ModelJogos;

public class ViewJogos extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel jogosPanel;
    private JPanel footerPanel;
    private List<JCheckBox> checkboxes; // Para rastrear os jogos comprados
    private JToggleButton toggleButton;
    private JRadioButton opcao1; // Padrão
    private JRadioButton opcao2; // Avançado

    public ViewJogos(List<ModelJogos> jogos) {
        setTitle("Loja de Jogos - Steam Keys");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLayout(new BorderLayout());

        checkboxes = new ArrayList<>();

        // Barra de menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opções");
        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(e -> System.exit(0));
        menu.add(sairItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Painel de jogos
        jogosPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        jogosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jogosPanel.setBackground(Color.LIGHT_GRAY);

        for (ModelJogos jogo : jogos) {
            JPanel jogoPanel = criarPainelJogo(jogo);
            jogosPanel.add(jogoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(jogosPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Rodapé
        footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        configurarFooter();
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel criarPainelJogo(ModelJogos jogo) {
        JPanel jogoPanel = new JPanel();
        jogoPanel.setLayout(new BoxLayout(jogoPanel, BoxLayout.Y_AXIS));
        jogoPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        jogoPanel.setBackground(Color.WHITE);

        JLabel imagemLabel = new JLabel(new ImageIcon(new ImageIcon(jogo.getImagemCaminho())
                .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nomeLabel = new JLabel(jogo.getNome());
        nomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField precoField = new JTextField(String.format("R$ %.2f", jogo.getPreco()));
        precoField.setHorizontalAlignment(JTextField.CENTER);
        precoField.setEditable(false);

        JButton gerarKeyButton = new JButton("Gerar Key");
        gerarKeyButton.setActionCommand(jogo.getNome());
        gerarKeyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JCheckBox comprarCheckBox = new JCheckBox(jogo.getNome()); // Usar o nome do jogo como texto
        comprarCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkboxes.add(comprarCheckBox);

        jogoPanel.add(imagemLabel);
        jogoPanel.add(nomeLabel);
        jogoPanel.add(precoField);
        jogoPanel.add(gerarKeyButton);
        jogoPanel.add(comprarCheckBox);

        return jogoPanel;
    }
    
    public boolean isModoAvancado() {
        return opcao2.isSelected();
    }

    private void configurarFooter() {
        opcao1 = new JRadioButton("Padrão", true);
        opcao2 = new JRadioButton("Modo Avançado");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcao1);
        grupo.add(opcao2);

        toggleButton = new JToggleButton("Modo Escuro");
        toggleButton.addActionListener(e -> alternarModoEscuro());

        JButton confirmarButton = new JButton("Confirmar Compra");
        confirmarButton.addActionListener(e -> confirmarCompra());

        footerPanel.add(opcao1);
        footerPanel.add(opcao2);
        footerPanel.add(toggleButton);
        footerPanel.add(confirmarButton);

        opcao1.addActionListener(e -> configurarModo(false));
        opcao2.addActionListener(e -> configurarModo(true));
    }

    private void configurarModo(boolean avancado) {
        for (Component c : jogosPanel.getComponents()) {
            if (c instanceof JPanel) {
                JPanel jogoPanel = (JPanel) c;
                jogoPanel.setBackground(avancado ? Color.LIGHT_GRAY : Color.WHITE);
                for (Component subComponent : jogoPanel.getComponents()) {
                    if (subComponent instanceof JButton) {
                        ((JButton) subComponent).setText(avancado ? "Key Avançada" : "Gerar Key");
                    }
                }
            }
        }
    }

    private void alternarModoEscuro() {
        boolean modoEscuro = toggleButton.isSelected();
        Color fundo = modoEscuro ? Color.DARK_GRAY : Color.LIGHT_GRAY;
        Color texto = modoEscuro ? Color.WHITE : Color.BLACK;

        jogosPanel.setBackground(fundo);
        footerPanel.setBackground(fundo);

        for (Component c : jogosPanel.getComponents()) {
            if (c instanceof JPanel) {
                JPanel jogoPanel = (JPanel) c;
                jogoPanel.setBackground(modoEscuro ? Color.GRAY : Color.WHITE);
                for (Component subComponent : jogoPanel.getComponents()) {
                    if (subComponent instanceof JLabel) {
                        ((JLabel) subComponent).setForeground(texto);
                    }
                }
            }
        }
    }

    private void confirmarCompra() {
        StringBuilder jogosComprados = new StringBuilder("Jogos comprados:\n");
        boolean algumComprado = false;

        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                jogosComprados.append("- ").append(checkbox.getText()).append("\n");
                algumComprado = true;
            }
        }

        if (!algumComprado) {
            jogosComprados = new StringBuilder("Nenhum jogo foi selecionado para compra!");
        }

        JOptionPane.showMessageDialog(this, jogosComprados.toString(), "Compra Confirmada", JOptionPane.INFORMATION_MESSAGE);
    }

    public JPanel getJogosPanel() {
        return jogosPanel;
    }
}
