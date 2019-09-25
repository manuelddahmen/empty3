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

/***
Global license : 

 CC Attribution

 author Manuel Dahmen <manuel.dahmen@gmx.com>

 ***/


package one.empty3.apps.pad;

import one.empty3.library.ColorTexture;
import one.empty3.library.ECBufferedImage;
import one.empty3.library.ImageTexture;
import one.empty3.library.Point3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 *
 * @author Manuel Dahmen <manuel.dahmen@gmx.com>
 */
public class Ciel {
    private TRISphere2 bleu;

    public Ciel() {
        bleu = new TRISphere2(null, new Point3D(0.5, 0.5, 0.0), 2);

        try {
            URL resource = getClass().getResource("one.empty3.library/apps/darz/marssurface.jpg");
            System.out.println(resource.toExternalForm());
            BufferedImage read = ImageIO.read(resource);
            ECBufferedImage ecBufferedImage = new ECBufferedImage(read);
            bleu.texture(new ImageTexture(ecBufferedImage));

        } catch (Exception  ex) {
            bleu.texture(new ColorTexture(Color.BLUE));
        }
    }

    public TRISphere2 getBleu() {
        return bleu;
    }


}
