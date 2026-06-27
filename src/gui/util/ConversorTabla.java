package gui.util;

import gui.componentes.Dato;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public final class ConversorTabla {

	private ConversorTabla() {

	}

	public static double[] convertirTabla(

			TableView<Dato> tabla) {

		ObservableList<Dato> datos = tabla.getItems();

		int cantidad = datos.size() - 1;

		double[] valores = new double[cantidad];

		for (int i = 0; i < cantidad; i++) {

			valores[i] = Double.parseDouble(datos.get(i).getValor());
		}

		return valores;
	}

}
