package view;

import model.Hewan;

public class KlinikView {
    public void cetakDetailHewan(Hewan hewan) {
        hewan.tampilkanInfo();
        System.out.println();
    }
}
