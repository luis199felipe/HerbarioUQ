/**
 * 
 */
package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.modelo.EmpleadoObservable;
import co.alfite.sis.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @author EinerZG
 */
public class EmpleadoControlador {

	/**
	 * table donde se almacena la informacion de los empleados
	 */
	@FXML
	private TableView<EmpleadoObservable> tablaEmpleados;
	/**
	 * hace referencia a la columna con las cedulas
	 */
	@FXML
	private TableColumn<EmpleadoObservable, String> cedulaColumna;
	/**
	 * hace referencia a la columna de los nombres de los empleados
	 */
	@FXML
	private TableColumn<EmpleadoObservable, String> nombreColumna;
	/**
	 * etiqueta de cedula
	 */
	@FXML
	private Label txtCedula;
	/**
	 * etiqueta de nombre
	 */
	@FXML
	private Label txtNombre;
	/**
	 * etiqueta de apellido
	 */
	@FXML
	private Label txtApellido;
	/**
	 * etiqueta de email
	 */
	@FXML
	private Label txtEmail;
	/**
	 * etiqueta de clave
	 */
	@FXML
	private Label txtClave;
	/**
	 * etiqueta de fecha
	 */
	@FXML
	private Label txtFechaNacimiento;
	/**
	 * instancia del manejador de escenario
	 */
	private ManejadorEscenarios escenarioInicial;

	private EmpleadoObservable empleadoObservable;

	public EmpleadoControlador() {
	}

	/**
	 * permite carga la informacion en las tables y escuchar las operaciones que se
	 * realizan con la tabla
	 */
	@FXML
	private void initialize() {

		cedulaColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getCedula());
		nombreColumna.setCellValueFactory(empleadoCelda -> empleadoCelda.getValue().getNombre());

		mostrarDetalleEmpleado(null);

		tablaEmpleados.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mostrarDetalleEmpleado(newValue));

	}

	/**
	 * permite obtener una instancia del escenario general
	 * 
	 * @param escenarioInicial
	 */
	public void setEscenarioInicial(ManejadorEscenarios escenarioInicial) {
		this.escenarioInicial = escenarioInicial;
		tablaEmpleados.setItems(escenarioInicial.getEmpleadosObservables());
	}

	/**
	 * permite mostrar la informacion del empleado seleccionado
	 * 
	 * @param empleado empleado al que se le desea mostrar el detalle
	 */
	public void mostrarDetalleEmpleado(EmpleadoObservable empleado) {

		if (empleado != null) {
			empleadoObservable = empleado;
			txtCedula.setText(empleado.getCedula().getValue());
			txtNombre.setText(empleado.getNombre().getValue());
			txtApellido.setText(empleado.getApellido().getValue());
			txtEmail.setText(empleado.getEmail().getValue());
			txtClave.setText(empleado.getClave().getValue());
			txtFechaNacimiento.setText(empleado.getFechaNacimiento().getValue().toString());
		} else {
			txtCedula.setText("");
			txtNombre.setText("");
			txtApellido.setText("");
			txtEmail.setText("");
			txtClave.setText("");
			txtFechaNacimiento.setText("");
		}

	}

	/**
	 * permite mostrar la ventana de agregar empleado
	 */
	@FXML
	public void agregarEmpleado() {
		escenarioInicial.cargarEscenarioCrearEmpleado();
		tablaEmpleados.refresh();
	}

	/**
	 * permite eliminar un empleado seleccionado
	 */
	@FXML
	public void elimiarEmpleado() {

		int indice = tablaEmpleados.getSelectionModel().getSelectedIndex();

		System.out.println(tablaEmpleados.getItems().size());

		Empleado empleado = tablaEmpleados.getItems().get(indice).getEmpleado();

//		if (escenarioInicial.eliminarEmpleado(empleado)) {
//			tablaEmpleados.getItems().remove(indice);
//			Utilidades.mostrarMensaje("Borrar", "El empleado ha sido eliminado con exito");
//		} else {
//			Utilidades.mostrarMensaje("Error", "El empleado no pudo ser eliminado");
//		}

	}

}
