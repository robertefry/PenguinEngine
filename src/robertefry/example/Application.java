
package robertefry.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.target.Targetable;
import robertefry.penguin.targets.FrameCounter;

/**
 * @author Robert E Fry
 * @date 29 Jul 2019
 */
public class Application implements Runnable {

	@Override
	public void run() {
		
		// create an engine that updates once every second
		Engine engine = new Engine();
		engine.setRefreshRate( 1 );
		
		// get the awt display component from the engine
		Component component = engine.getRenderer().getComponent();
		component.setPreferredSize( new Dimension( 800, 600 ) );
		
		// create a JFrame for the engine to display to
		JFrame frame = new JFrame();
		frame.getContentPane().add( component );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
		
		// create an engine target that will be the game
		/*
		 * 'Targetable' is the minimal interface needed for interaction with an engine
		 * 'TargetBlank' is the default implementation for Targetable
		 * 'Target' is a Targetable that allows for sub-targets wherein all calls to the
		 * 		target are also passed down to the sub-targets
		 * 'TargetManager' extends upon the Target implentation by acting as a collection 
		 * 		of its sub-targets while still allowing for interaction with an engine
		 */
		Targetable game = new Game();
		engine.getTargetManager().add( game );
		
		// add a frame counter just because I can
		Targetable framecounter = new FrameCounter( System.out, 1000 );
		engine.getTargetManager().add( framecounter );
		
		// finally, start the engine
		engine.start();
		
	}
	
	private class Game implements Targetable {
		
		private boolean initialised = false;
		
		private Random rand = new Random( System.currentTimeMillis() );
		private Color drawColor = Color.BLACK;

		@Override
		public void init() {
			// initialise stuff when this target is first used by the engine
			initialised = true;
		}

		@Override
		public void dispose() {
			// dispose of stuff when this target is no longer used by the engine
			initialised = false;
		}

		@Override
		public boolean isInitialized() {
			return initialised;
		}

		@Override
		public void pollInput() {
			// processing for any input devices
		}

		@Override
		public void update() {
			// set a random background color to draw
			drawColor = new Color( rand.nextInt() );
		}

		@Override
		public void render( Graphics g ) {
			// draw a box
			g.setColor( drawColor );
			g.fillRect( 10, 10, 100, 100 );
		}

		@Override
		public void reset() {
		}
		
	}
	
}
