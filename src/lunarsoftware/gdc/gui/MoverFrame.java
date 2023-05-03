package lunarsoftware.gdc.gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MoverFrame extends MouseAdapter {

        private final JFrame frame;
        private Point mouseComp = null;

        public MoverFrame(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseComp = null;
        }

        public void mousePressed(MouseEvent e) {
        	//AL mouse seleccionar el contenedor le retorna un Point
            mouseComp = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
        	/**Al mause ser arrastrado dentro del contenedor obtiene las cordenadas
        	 * y relocaliza la ventana al lugar en donde se encuentra el mouse
        	 */
            Point cords = e.getLocationOnScreen();
            frame.setLocation(cords.x - mouseComp.x, cords.y - mouseComp.y);
        }
    }