/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package one.empty3.apps.pad;


import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import one.empty3.apps.pad.menu.ToggleMenu;



/*__
 * Created by manuel on 07-06-17.
 */
public class DarkFortressGUIKeyListener implements KeyListener, Runnable {
    private final PositionUpdate mover;
    protected boolean release_up = true;
    protected boolean release_down = true;
    protected boolean release_left = true;
    protected boolean release_right = true;
    protected boolean release_space = true;
    protected boolean ctrl = false;
    ToggleMenu toggleMenu;

    public DarkFortressGUIKeyListener(PositionUpdate mover) {
        this.mover = mover;
        toggleMenu = new ToggleMenu();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()== 't')
            toggleMenu.setDisplayMenu(!toggleMenu.isDisplayMenu());
        if (e.getKeyChar()== '+')
            toggleMenu.setIndex(toggleMenu.getIndex()+1);
        if (e.getKeyChar()== '-')
            toggleMenu.setIndex(toggleMenu.getIndex()-1);
        if (e.getKeyChar()== '\n')
            toggleMenu.setOption(toggleMenu.getIndex(), !toggleMenu.getOption(toggleMenu.getIndex()));
        if (e.getKeyCode() == KeyEvent.VK_CONTROL
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            ctrl = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_space = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            ctrl = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_up = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE
                && mover.state() == mover.STATE_GAME_IN_PROGRESS()) {
            release_space = true;
        }
    }

    private void cont(long timeKeyPress) {
        if(!ctrl) {
            if (!release_up) {
                mover.acc(timeKeyPress);
                //System.out.println("Acc");
            }
            if (!release_down) {
                mover.dec(timeKeyPress);
                //System.out.println("Dec");
            }
            if (!release_left) {
                mover.rotationGauche(timeKeyPress);
                //System.out.println("Left");
            }
            if (!release_right) {
                mover.rotationDroite(timeKeyPress);
                //System.out.println("Right");
            }
            if(!release_space) {
                mover.moveUp(timeKeyPress);
            }

        }
    }


    @Override
    public void run() {
        long timeBefore = System.nanoTime();
        long timeAfter = timeBefore;
        while (true) {
            timeBefore = System.nanoTime();

            cont(timeAfter - timeBefore);
            timeAfter = System.nanoTime();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
