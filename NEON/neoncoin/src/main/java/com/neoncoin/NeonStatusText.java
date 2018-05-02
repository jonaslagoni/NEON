/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoncoin;

import com.library.interfaces.INeonService;
import com.library.interfaces.IStatusText;

/**
 * @author emil
 */
public class NeonStatusText implements IStatusText {

    private final String label;
    private final INeonService neonWallet;

    NeonStatusText(String label, INeonService neonWallet) {
        this.label = label;
        this.neonWallet = neonWallet;
    }

    @Override
    public String update() {
        return label + neonWallet.getCoins();
    }

}
