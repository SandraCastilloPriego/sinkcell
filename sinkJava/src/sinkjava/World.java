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

import java.util.Random;

/**
 *
 * @author sandra
 */
public class World {

    private int[][] cellsState;
    private int[][] proximity;
    private int[][] habitat;
    private int x_axis;
    private int y_axis;
    private int n_population;
    private int mortality;
    private int patch;
    private Parameters parameters;

    public World(Parameters parameters) {

        this.x_axis = parameters.size+1;
        this.y_axis = parameters.size+1;
        this.parameters = parameters;
        this.cellsState = new int[this.x_axis][];
        for(int i = 0; i < this.x_axis; i++){
            this.cellsState[i] = new int[this.y_axis];
        }
        this.proximity = new int[this.x_axis][];
        for(int i = 0; i < this.x_axis; i++){
            this.proximity[i] = new int[this.y_axis];
        }
        this.habitat = new int[this.x_axis][];
        for(int i = 0; i < this.x_axis; i++){
            this.habitat[i] = new int[this.y_axis];
        }

        for (int i = 0; i < this.x_axis; i++) {
            for (int e = 0; e < this.y_axis; e++) {
                this.cellsState[i][e] = 0;
                this.proximity[i][e] = 0;
                this.habitat[i][e] = 0;
            }
        }

        this.n_population = parameters.population;
        this.mortality = parameters.mortality;
        this.patch = parameters.patch;

        this.init();
    }

    //Start World's values
    public void init() {
        int x = 0, y = 0;
        Random oRand = new Random();
        //Init the "habitat" array
        for (int i = 0; i < this.patch; i++) {
            x = (oRand.nextInt(this.x_axis - 1) + 1);
            y = (oRand.nextInt(this.y_axis - 1) + 1);

            for (int j = y - 1; j < y + 2; j++) {
                for (int e = x - 1; e < x + 2; e++) {
                    this.habitat[this.normalize(e, this.x_axis)][this.normalize(j, this.y_axis)] = 1;
                }
            }
        }

        //Init the "population" array
        for (int i = 0; i < this.n_population; i++) {
            x = (oRand.nextInt(this.x_axis - 1) + 1);
            y = (oRand.nextInt(this.y_axis - 1) + 1);
            this.cellsState[x][y] = 1;
        }

    }

    public int normalize(int coord, int limit) {
        if (coord < 0) {
            return (limit - Math.abs(coord)) % limit;
        } else if (coord >= limit) {
            return coord - limit;
        } else {
            return coord;
        }
    }

    //Add the neighbours of every square at "proximity" array
    public void densityCalculus() {
        for (int x = 0; x < this.x_axis; x++) {
            for (int y = 0; y < this.y_axis; y++) {
                this.proximity[x][y] = 0;
                for (int j = y - 1; j < y + 2; j++) {
                    for (int e = x - 1; e < x + 2; e++) {
                        if (j != y || e != x) {
                            this.proximity[x][y] += this.cellsState[this.normalize(j, this.x_axis)][this.normalize(e, this.y_axis)];
                        }
                    }
                }
            }
        }
    }

    public void update() {
        int x = 0, y = 0;
        Random oRand = new Random();
        for (x = 0; x < this.x_axis; x++) {
            for (y = 0; y < this.y_axis; y++) {
                if (this.cellsState[x][y] == 1) {
                    if ((this.proximity[x][y] >= parameters.var1 && this.habitat[x][y] == 1) || (this.proximity[x][y] >= parameters.var2 && this.habitat[x][y] == 0)) {
                        this.cellsState[x][y] = 0;
                    }
                } else {
                    if ((this.proximity[x][y] >= parameters.var3 && this.habitat[x][y] == 1) || (this.proximity[x][y] >= parameters.var4 && this.habitat[x][y] == 0)) {
                        this.cellsState[x][y] = 1;
                    }
                }
            }
        }

        //Mortality
        for (int i = 0; i < this.mortality; i++) {
            x = (oRand.nextInt(this.x_axis - 1) + 1);
            y = (oRand.nextInt(this.y_axis - 1) + 1);
            this.cellsState[x][y] = 0;
        }
    }

    public void cicle() {
        this.densityCalculus();
        this.update();
    }

    public int getPopulation() {
        int locaPopulation = 0;
        for (int x = 0; x < this.x_axis; x++) {
            for (int y = 0; y < this.y_axis; y++) {
                locaPopulation += this.cellsState[x][y];
            }
        }
        return locaPopulation;
    }

    public float getDensity() {
        float density = (float)this.getPopulation() / (this.x_axis * this.y_axis);
        return density;
    }

    public float getProximity() {
        float localProximity = (float)this.getPopulation() / ((this.x_axis * this.y_axis) / 8);
        return localProximity;
    }

    public int[][] getPopulationArray() {
        return this.cellsState;
    }

    public int[][] getHabitatArray() {
        return this.habitat;
    }

    public Parameters getParameters(){
        return this.parameters;
    }
}
