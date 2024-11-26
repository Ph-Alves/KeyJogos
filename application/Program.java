package application;

import java.util.ArrayList;
import java.util.List;

import control.ControlJogos;
import model.ModelJogos;
import view.ViewJogos;

public class Program {

	public static void main(String[] args) {
       
		List<ModelJogos> jogos = new ArrayList<>();
        jogos.add(new ModelJogos("Cyberpunk 2077", 199.99, "D:\\Importante\\programas_linguagens\\EscolaMusica\\keysJogos\\Images\\cyberpunk.jpg"));
        jogos.add(new ModelJogos("God of War", 149.99, "D:\\Importante\\programas_linguagens\\EscolaMusica\\keysJogos\\Images\\godOfWar.jpg"));
        jogos.add(new ModelJogos("Minecraft", 99.99, "D:\\Importante\\programas_linguagens\\EscolaMusica\\keysJogos\\Images\\minecraft.jpg"));
        jogos.add(new ModelJogos("Elden Ring", 249.90, "D:\\Importante\\programas_linguagens\\EscolaMusica\\keysJogos\\Images\\eldenRing.jpg"));
        jogos.add(new ModelJogos("The Legend of Zelda: Breath of the Wild", 299.90, "D:\\Importante\\programas_linguagens\\EscolaMusica\\keysJogos\\Images\\zelda.png"));
        jogos.add(new ModelJogos("Hollow Knight", 57.99, "D:\\Importante\\programas_linguagens\\EscolaMusica\\keysJogos\\Images\\hollowKnight.jpg"));

        ViewJogos view = new ViewJogos(jogos);
        new ControlJogos(view, jogos);

        view.setVisible(true);
    }
}
