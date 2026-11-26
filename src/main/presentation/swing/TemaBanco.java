package presentation.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Identidade visual compartilhada do Banco ES.
 * Esta classe altera exclusivamente propriedades visuais dos componentes Swing.
 */
final class TemaBanco {

    private static final Color FUNDO = new Color(10, 16, 34);
    private static final Color CARTAO = new Color(22, 32, 57);
    private static final Color CARTAO_INTERNO = new Color(27, 40, 68);
    private static final Color AZUL = new Color(48, 119, 230);
    private static final Color AZUL_HOVER = new Color(37, 99, 202);
    private static final Color VERDE = new Color(66, 211, 145);
    private static final Color VERMELHO = new Color(220, 82, 94);
    private static final Color TEXTO = new Color(241, 246, 255);
    private static final Color TEXTO_SUAVE = new Color(164, 179, 204);
    private static final Color TEXTO_ESCURO = new Color(24, 35, 55);
    private static final Color BORDA = new Color(48, 65, 96);
    private static final Color CAMPO = new Color(245, 248, 253);

    private static final Font FONTE_BASE = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 21);
    private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 14);

    private TemaBanco() { }

    static void aplicar(JFrame janela) {
        janela.getContentPane().setBackground(FUNDO);
        janela.getContentPane().setForeground(TEXTO);
        janela.getContentPane().setFont(FONTE_BASE);

        janela.setMinimumSize(new Dimension(
                Math.min(Math.max(janela.getWidth(), 520), 760),
                Math.min(Math.max(janela.getHeight(), 420), 620)));

        estilizar(janela.getContentPane(), 0);
        janela.getContentPane().revalidate();
        janela.getContentPane().repaint();
    }

    private static void estilizar(Component componente, int nivel) {
        if (componente instanceof JPanel) {
            JPanel painel = (JPanel) componente;
            painel.setBackground(nivel == 0 ? FUNDO
                    : nivel == 1 ? CARTAO : CARTAO_INTERNO);
            painel.setOpaque(true);

            if (nivel == 1) {
                painel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDA, 1, true),
                        BorderFactory.createEmptyBorder(14, 16, 14, 16)));
            } else if (nivel > 1) {
                painel.setBorder(BorderFactory.createEmptyBorder(4, 5, 4, 5));
            }
        }

        if (componente instanceof JLabel) {
            estilizarLabel((JLabel) componente);
        }

        if (componente instanceof AbstractButton) {
            estilizarBotao((AbstractButton) componente);
        }

        if (componente instanceof JTextField) {
            estilizarCampo((JTextField) componente);
        } else if (componente instanceof JTextArea) {
            JTextArea area = (JTextArea) componente;
            area.setFont(FONTE_BASE);
            area.setBackground(CAMPO);
            area.setForeground(TEXTO_ESCURO);
            area.setCaretColor(TEXTO_ESCURO);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setBorder(campoBorda());
        }

        if (componente instanceof JComboBox) {
            JComboBox<?> combo = (JComboBox<?>) componente;
            combo.setFont(FONTE_BASE);
            combo.setBackground(CAMPO);
            combo.setForeground(TEXTO_ESCURO);
            combo.setFocusable(true);
            combo.setBorder(campoBorda());
        }

        if (componente instanceof JSpinner) {
            JSpinner spinner = (JSpinner) componente;
            spinner.setFont(FONTE_BASE);
            spinner.setBorder(campoBorda());
            JComponent editor = spinner.getEditor();
            if (editor instanceof JSpinner.DefaultEditor) {
                ((JSpinner.DefaultEditor) editor).getTextField()
                        .setBackground(CAMPO);
                ((JSpinner.DefaultEditor) editor).getTextField()
                        .setForeground(TEXTO_ESCURO);
            }
        }

        if (componente instanceof JTable) {
            estilizarTabela((JTable) componente);
        }

        if (componente instanceof JScrollPane) {
            JScrollPane scroll = (JScrollPane) componente;
            scroll.setBorder(BorderFactory.createLineBorder(BORDA, 1, true));
            scroll.getViewport().setBackground(CAMPO);
        }

        if (componente instanceof JTabbedPane) {
            JTabbedPane abas = (JTabbedPane) componente;
            abas.setFont(FONTE_BASE.deriveFont(Font.BOLD));
            abas.setForeground(TEXTO);
            abas.setBackground(CARTAO);
        }

        if (componente instanceof Container) {
            Container container = (Container) componente;
            for (Component filho : container.getComponents()) {
                estilizar(filho, nivel + 1);
            }
        }
    }

    private static void estilizarLabel(JLabel label) {
        Color atual = label.getForeground();
        Color padrao = UIManager.getColor("Label.foreground");

        if (atual == null || atual.equals(padrao) || atual.equals(Color.BLACK)) {
            label.setForeground(TEXTO);
        }

        Font fonte = label.getFont();
        if (fonte == null) {
            label.setFont(FONTE_BASE);
        } else if (fonte.getSize() >= 25 || fonte.isBold() && fonte.getSize() >= 20) {
            label.setFont(new Font("Segoe UI", Font.BOLD, fonte.getSize()));
        } else if (fonte.getSize() < 13) {
            label.setFont(FONTE_BASE.deriveFont(13f));
        } else {
            label.setFont(new Font("Segoe UI", fonte.getStyle(), fonte.getSize()));
        }
    }

    private static void estilizarBotao(AbstractButton botao) {
        String texto = botao.getText() == null
                ? "" : botao.getText().trim().toLowerCase();

        boolean perigoso = texto.contains("excluir") || texto.contains("remover")
                || texto.contains("sair") || texto.contains("cancelar");
        boolean secundario = texto.contains("voltar") || texto.contains("limpar");

        Color fundo = perigoso ? VERMELHO : secundario ? CARTAO_INTERNO : AZUL;
        Color frente = Color.WHITE;

        botao.setFont(FONTE_BOTAO);
        botao.setForeground(frente);
        botao.setBackground(fundo);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setFocusPainted(false);
        botao.setFocusable(true);
        botao.setMargin(new Insets(9, 17, 9, 17));

        if (botao instanceof BotaoArredondado) {
            botao.setContentAreaFilled(false);
            botao.setBorderPainted(false);
            botao.setOpaque(false);
        } else {
            botao.setOpaque(true);
            botao.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            secundario ? BORDA : fundo.darker(), 1, true),
                    BorderFactory.createEmptyBorder(9, 16, 9, 16)));
        }

        botao.setRolloverEnabled(true);
        botao.setToolTipText(botao.getToolTipText());
    }

    private static void estilizarCampo(JTextField campo) {
        campo.setFont(FONTE_BASE);
        campo.setBackground(CAMPO);
        campo.setForeground(TEXTO_ESCURO);
        campo.setCaretColor(AZUL);
        campo.setSelectionColor(new Color(195, 219, 255));
        campo.setSelectedTextColor(TEXTO_ESCURO);
        campo.setBorder(campoBorda());
        campo.setPreferredSize(new Dimension(
                Math.max(campo.getPreferredSize().width, 190), 40));
    }

    private static Border campoBorda() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 216, 232), 1, true),
                BorderFactory.createEmptyBorder(8, 11, 8, 11));
    }

    private static void estilizarTabela(JTable tabela) {
        tabela.setFont(FONTE_BASE);
        tabela.setRowHeight(Math.max(34, tabela.getRowHeight()));
        tabela.setForeground(TEXTO_ESCURO);
        tabela.setBackground(Color.WHITE);
        tabela.setGridColor(new Color(226, 233, 243));
        tabela.setSelectionBackground(new Color(215, 231, 255));
        tabela.setSelectionForeground(TEXTO_ESCURO);
        tabela.setShowVerticalLines(false);
        tabela.setIntercellSpacing(new Dimension(0, 1));

        JTableHeader cabecalho = tabela.getTableHeader();
        if (cabecalho != null) {
            cabecalho.setFont(new Font("Segoe UI", Font.BOLD, 13));
            cabecalho.setBackground(CARTAO);
            cabecalho.setForeground(TEXTO);
            cabecalho.setPreferredSize(new Dimension(
                    cabecalho.getPreferredSize().width, 38));
            cabecalho.setReorderingAllowed(false);
        }
    }
}
