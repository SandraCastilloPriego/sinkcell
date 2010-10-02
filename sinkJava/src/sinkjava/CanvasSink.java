/*
 * Copyright 2008-2010 The sinkCell Development Team
 *
 * This file is part of sinkCell.
 *
 * sinkCell is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * sinkCell is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * sinkCell; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */
package sinkjava;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author sandra
 */
public class CanvasSink extends Canvas {

    private int canvasSize;
    private World world;
    private Parameters parameters;
    Graphics offgc;
    Image offscreen = null;

    public CanvasSink(World world, Parameters parameters) {
        canvasSize = 600 ;
        this.parameters = parameters;
        this.world = world;
        this.setSize(canvasSize, canvasSize);
        this.setBackground(Color.white);
    }

    @Override
    public void update(Graphics g) {
        try {
            offscreen = createImage(canvasSize, canvasSize);
            offgc = offscreen.getGraphics();

            int[][] population = this.world.getPopulationArray();
            int[][] habitat = this.world.getHabitatArray();

            int size = parameters.size;

            for (int x = 0; x < size - 1; x++) {
                for (int y = 0; y < size - 1; y++) {
                    int n_x = ((this.canvasSize * x) / size);
                    int n_y = ((this.canvasSize * y) / size);
                  //  System.out.println(x + " - " + y);
                    if (habitat[x][y] == 1) {
                        offgc.setColor(Color.black);
                        offgc.drawRect(n_x, n_y, (this.canvasSize / size) - 2, (this.canvasSize / size) - 2);
                        offgc.setColor(Color.GREEN);
                        offgc.fillRect(n_x, n_y, this.canvasSize / size, this.canvasSize / size);
                    }

                    if (population[x][y] == 1) {
                        offgc.setColor(Color.red);
                        offgc.fillRect(n_x, n_y, (this.canvasSize / size) - 1, (this.canvasSize / size) - 1);
                    }
                }
            }           
            // transfer offscreen to window
            g.drawImage(offscreen, 0, 0, this);
     
        } catch (Exception e) {
        }

    }

   
}



