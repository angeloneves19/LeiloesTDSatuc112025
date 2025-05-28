import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class TelaVendas extends JFrame {
    private JTable tabelaVendas;
    private ProdutosDAO produtosDAO;

    public TelaVendas() {
        produtosDAO = new ProdutosDAO();
        List<ProdutosDTO> produtosVendidos = produtosDAO.listarProdutosVendidos();
        String[] colunas = {"ID", "Nome", "Status"};
        Object[][] dados = new Object[produtosVendidos.size()][3];
        for (int i = 0; i < produtosVendidos.size(); i++) {
            dados[i][0] = produtosVendidos.get(i).getId();
            dados[i][1] = produtosVendidos.get(i).getNome();
            dados[i][2] = produtosVendidos.get(i).getStatus();
        }
        tabelaVendas = new JTable(dados, colunas);
        add(new JScrollPane(tabelaVendas));
        setTitle("Tela de Vendas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}