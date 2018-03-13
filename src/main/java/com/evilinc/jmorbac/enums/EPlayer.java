/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evilinc.jmorbac.enums;

/**
 *
 * @author teton
 */
public enum EPlayer {
    CROSS(-1, "X"),
    CIRCLE(1, "O");
    
    private final int gridPawnValue;
    private final String consolePawnValue;
    
    private EPlayer(final int gridPawnValue, final String consolePawnValue) {
        this.gridPawnValue = gridPawnValue;
        this.consolePawnValue = consolePawnValue;
    }

    public String getConsolePawnValue() {
        return consolePawnValue;
    }

    public int getGridPawnValue() {
        return gridPawnValue;
    }
}
