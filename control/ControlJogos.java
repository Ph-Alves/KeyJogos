package control;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.ModelJogos;
import view.ViewJogos;

public class ControlJogos {
	private ViewJogos view;
    private List<ModelJogos> jogos;

    public ControlJogos(ViewJogos view, List<ModelJogos> jogos) {
        this.view = view;
        this.jogos = jogos;

        // Adiciona listeners para cada botão
        for (Component component : view.getJogosPanel().getComponents()) {
            if (component instanceof JPanel) {
                JPanel jogoPanel = (JPanel) component;
                for (Component subComponent : jogoPanel.getComponents()) {
                    if (subComponent instanceof JButton) {
                        JButton keyButton = (JButton) subComponent;
                        keyButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String nomeJogo = keyButton.getActionCommand();
                                gerarKeyParaJogo(nomeJogo);
                            }
                        });
                    }
                }
            }
        }
    }

    private void gerarKeyParaJogo(String nomeJogo) {
        for (ModelJogos jogo : jogos) {
            if (jogo.getNome().equals(nomeJogo)) {
                String key;

                // Verifica o modo avançado
                if (view.isModoAvancado()) {
                    key = "ADV-" + jogo.gerarKey();
                } else {
                    key = jogo.gerarKey();
                }

                // Abre nova janela com a key
                JFrame keyFrame = new JFrame("Key Gerada");
                keyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                keyFrame.setSize(500, 300); // Aumentando o tamanho da janela

                JLabel keyLabel = new JLabel("<html><center><b>Sua Key: " + key + "</b></center></html>", SwingConstants.CENTER);
                keyLabel.setFont(new Font("Arial", Font.BOLD, 18));

                keyFrame.add(keyLabel);
                keyFrame.setVisible(true);
                break;
            }
        }
    }
    
    
}
