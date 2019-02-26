
package robertefry.penguin.engine.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Collection;
import javax.swing.JPanel;
import robertefry.penguin.target.Targetable;

/**
 * @author Robert E Fry
 * @date 26 Feb 2019
 */
public class Renderer {

	private final Collection< Targetable > targets;
	private final JPanel canvas = new InstanceCanvas();

	public Renderer( Collection< Targetable > targets ) {
		this.targets = targets;
	}

	public void render() {
		canvas.repaint();
	}

	public Component getComponent() {
		return canvas;
	}

	private final class InstanceCanvas extends JPanel {
		private static final long serialVersionUID = 2900713581555088356L;

		public InstanceCanvas() {
			setBackground( Color.BLACK );
		}

		@Override
		protected void paintComponent( Graphics g ) {
			super.paintComponent( g );
			targets.forEach( target -> target.render( g ) );
		}

	}

}
