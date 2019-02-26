
package robertefry.penguin.engine.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JPanel;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.listener.EngineLogicAdapter;
import robertefry.penguin.target.Targetable;

/**
 * @author Robert E Fry
 * @date 26 Feb 2019
 */
public class Renderer {

	private final Engine engine;
	private final JPanel canvas = new InstanceCanvas();

	public Renderer( Engine engine ) {

		this.engine = engine;
		canvas.setBackground( Color.BLACK );
		
		engine.getEngineLogicListeners().add( new EngineLogicAdapter() {
			public void postRender() {
				canvas.repaint();
			}
		} );

	}

	public void render() {
		canvas.repaint();
	}

	public Graphics getGraphics() {
		return canvas.getGraphics();
	}

	public Component getComponent() {
		return canvas;
	}

	private final class InstanceCanvas extends JPanel {
		private static final long serialVersionUID = 2900713581555088356L;

		@Override
		protected void paintComponent( Graphics g ) {
			super.paintComponent( g );
			engine.getTargetManager().forEach( Targetable::render );
		}

	}

}
