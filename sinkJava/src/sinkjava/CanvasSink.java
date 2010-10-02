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
import java.awt.Event;
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

    /**
     * protected virtual void OnDrawingarea1ExposeEvent(object o, Gtk.ExposeEventArgs args)
    {
    Gtk.Widget DrawingArea = (Gtk.Widget)o;
    Gdk.Color color =  new Gdk.Color((byte)255,(byte)255,(byte)255);
    this.gc = new Gdk.GC(DrawingArea.GdkWindow);
    this.gc.RgbFgColor = color;
    DrawingArea.GdkWindow.DrawRectangle(this.gc,true,
    0,
    0,
    this.limit*2,
    this.limit*2);

    //If the simulation isn't stopped (Mark = 0):
    if(Mark == 0){
    int[,] population = this.world_start.get_population_array();
    int[,] habitat = this.world_start.get_habitat_array();

    for (int x = 0; x < this.Size; x++) {
    for (int y = 0; y < this.Size; y++) {
    int n_x = ((this.limit*x)/this.Size);
    int n_y = ((this.limit*y)/this.Size);
    if(habitat[x,y] == 1)
    {
    color = new Gdk.Color((byte)0,(byte)255,(byte)0);
    this.gc.RgbFgColor = color;
    DrawingArea.GdkWindow.DrawRectangle(this.gc,true,
    n_x,
    n_y,
    this.limit/this.Size,
    this.limit/this.Size);

    color = new Gdk.Color((byte)0,(byte)0,(byte)0);
    this.gc.RgbFgColor = color;
    DrawingArea.GdkWindow.DrawRectangle(this.gc,false,
    n_x,
    n_y,
    this.limit/this.Size,
    this.limit/this.Size);x
    }
    if(population[x,y] == 1){
    color = new Gdk.Color((byte)255,(byte)0,(byte)0);
    this.gc.RgbFgColor = color;
    DrawingArea.GdkWindow.DrawRectangle(this.gc,true,
    n_x,
    n_y,
    (this.limit/this.Size)-1,
    (this.limit/this.Size)-1);
    }
    }
    }
    update_world();
    }
    }

     * @param g
     */
    public void paint(Graphics g) {
        int[][] population = this.world.getPopulationArray();
        int[][] habitat = this.world.getHabitatArray();

        int xSize = this.world.getParameters().x;
        int ySize = this.world.getParameters().y;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                int n_x = ((this.w * x) / xSize);
                int n_y = ((this.h * y) / ySize);
                if (habitat[x][y] == 1) {
                    g.setColor(Color.black);
                    g.drawRect(n_x, n_y, this.w / xSize, this.h / ySize);
                    g.setColor(Color.GREEN);
                    g.fillRect(n_x, n_y, this.w / xSize, this.h / ySize);
                }
            }
        }
        /*color = new Gdk.Color((byte)0,(byte)0,(byte)0);
        this.gc.RgbFgColor = color;
        DrawingArea.GdkWindow.DrawRectangle(this.gc,false,
        n_x,
        n_y,
        this.limit/this.Size,
        this.limit/this.Size);
        }*/
        //  g.setColor(Color.blue);
        //  g.fillRect(0, 0, w, h);
    }

    public boolean mouseDown(Event evt, int x, int y) {
        if (x < w && y < h) {
            System.out.println("Raton en Canvas: (" + x + "," + y + ")");
            return true;
        }
        return false;
    }
}



