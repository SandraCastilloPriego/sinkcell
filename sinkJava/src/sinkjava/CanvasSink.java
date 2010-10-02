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

/**
 *
 * @author sandra
 */
public class CanvasSink extends Canvas {

    private int w;
    private int h;
    private World world;

    public CanvasSink(World world) {
        w = 600;
        h = 600;
        this.world = world;
        this.setSize(h, w);
        this.setBackground(Color.white);
    }
    
    @Override
    public void paint(Graphics g) {
        int[][] population = this.world.getPopulationArray();
        int[][] habitat = this.world.getHabitatArray();

        int size = this.world.getParameters().size;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int n_x = ((this.w * x) / size);
                int n_y = ((this.h * y) / size);
                if (habitat[x][y] == 1) {
                    g.setColor(Color.black);
                    g.drawRect(n_x, n_y, this.w / size, this.h / size);
                    g.setColor(Color.GREEN);
                    g.fillRect(n_x, n_y, this.w / size, this.h / size);
                }

                if (population[x][y] == 1) {
                    g.setColor(Color.red);
                    g.fillRect(n_x, n_y, (this.w / size) - 1, (this.h / size) - 1);
                }
            }
        } 
    }
   
}



