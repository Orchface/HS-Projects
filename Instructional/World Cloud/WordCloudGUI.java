import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class WordCloudGUI {
	/**
	 * Note: This class is designed for compatibility with Mr. Bunn's Word Cloud lab:
	 * https://www.dropbox.com/s/zqsfcas46egcqzl/WordCloud.docx?dl=0
	 */

	private ArrayList<Word> cloud;
	private JLabel[] labels;

    public static void main(String[] args) throws IOException
    {
        WordCloud w = new WordCloud("data/dream.txt"); //replace with your file's location, mine is in a folder called data
        //if your file is in the project folder (root directory), you don't have to specify a folder location, just use "dream.txt"
        w.printInfo();
        WordCloudGUI.run(w.getTopHits());
    }
    
	public static void run(ArrayList<Word> wordList) { // Call this method to run the UI. Note that it is static.
		

		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
	
				
				
				new WordCloudGUI().initUI(wordList);
			}
		});
	}

	// The methods below should not be edited.

	protected void initUI(ArrayList<Word> wds) {
		this.labels = new JLabel[wds.size()];
		this.cloud = wds;
		JPanel panel = new JPanel(new RowFormat());
		JFrame frame = new JFrame(WordCloudGUI.class.getSimpleName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Component c = new JLabel();
		FontMetrics metrics;
		String[] fonts = new String[] { "Tahoma", "Aharoni", "Arial", "Times New Roman", "Cambria" };
		Font font = new Font(fonts[new Random().nextInt(fonts.length)], Font.PLAIN, 0);
		for (int i = 0; i < this.cloud.size(); i++) {
			JLabel l = new JLabel("  " + cloud.get(i).getWord() + "  ");
			l.setOpaque(false);
			l.setFont(new Font(font.getFontName(), Font.PLAIN,
					cloud.get(i).getCount() < 12 ? 12 : cloud.get(i).getCount()));
			labels[i] = l;
		}
		for (int i = 0; i < this.labels.length; i++) {

			panel.add(labels[i]);
		}

		JScrollPane pane = new JScrollPane(panel);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		frame.add(pane);
		frame.setSize(500, 400);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public class RowFormat extends FlowLayout {
		public RowFormat() {
			super();
		}

		@Override
		public Dimension preferredLayoutSize(Container c) {
			return layoutSize(c, true);
		}

		@Override
		public Dimension minimumLayoutSize(Container c) {
			Dimension minimum = layoutSize(c, false);
			minimum.width -= getHgap() + 1;
			return minimum;
		}

		private void addRow(Dimension d, int w, int h) {
			d.width = Math.max(d.width, w);
			d.height += d.height > 0 ? getVgap() : 0;
			d.height += h;
		}

		private Dimension layoutSize(Container c, boolean b) {
			int width = c.getSize().width <= 0 ? Integer.MAX_VALUE : c.getSize().width;
			int horizontal = getHgap();
			int vertical = getVgap();
			Insets insets = c.getInsets();
			int insetPadding = insets.left + insets.right + horizontal * 2;
			int max = width - insetPadding;
			Dimension d = new Dimension(0, 0);
			int w = 0;
			int h = 0;
			int nmembers = c.getComponentCount();
			for (int i = 0; i < nmembers; i++) {
				Component m = c.getComponent(i);
				if (m.isVisible()) {
					Dimension tmp = b ? m.getPreferredSize() : m.getMinimumSize();
					if (w + tmp.width > max) {
						addRow(d, w, h);
						w = 0;
						h = 0;
					}
					w += tmp.width;
					w += w != 0 ? horizontal : 0;
					h = Math.max(h, tmp.height);
				}
			}
			addRow(d, w, h);
			d.width += insetPadding;
			d.height += insets.top + insets.bottom + vertical * 2;
			Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, c);
			d.width -= scrollPane != null ? horizontal + 1 : 0;
			return d;
		}
	}
}